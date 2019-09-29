package com.chekuri.state;

import com.chekuri.TokenizerContext;

import java.util.HashSet;
import java.util.List;

import com.chekuri.floki.io.reader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static com.chekuri.utilities.StringUtilities.isEnglishLetter;

abstract public class TokenizerState {
    protected Logger logger = LogManager.getLogger(this);
    protected TokenizerContext context;

    protected boolean collectChar() {
        char currentChar = context.getNext();
        context.previousCharacter = context.currentCharacter;
        context.currentCharacter = currentChar;
        if(currentChar == '\0') {
            return false;
        }
        return true;
    }

    protected boolean isOneCharToken(String token) {
        return context.oneCharTokens.contains(token);
    }
    protected boolean isTwoCharToken(String token) { return context.twoCharTokens.contains(token); }

    protected String makeString(char charToken) {
        String result = "";
        result += charToken;
        return result;
    }

    public void attach(reader _inputStream) {
        context.inputStream = _inputStream;
    }

    abstract public void eatChars();

    public void consumeCharacters() {
        context.currentState.eatChars();
        context.currentState = nextState();
    }

    public boolean canRead() {
        return context.inputStream.good();
    }

    public String getToken() {
        return context.token;
    }

    public boolean hasToken() {
        return context.token.length() > 0;
    }

    public void setOneCharTokens(List<String> _oneCharTokens) {
        context.oneCharTokens = new HashSet<>(_oneCharTokens);
    }
    public void setTwoCharTokens(List<String> _twoCharTokens) { context.twoCharTokens = new HashSet<>(_twoCharTokens); }

    public void setContext(TokenizerContext _context) {
        context = _context;
    }

    public int currentLineCount() {
        return context.lineCount;
    }

    public boolean terminationCondition() {
        boolean result = false;
        if(!context.inputStream.good()) {
            result = true;
        }
        return result;
    }

    public boolean checkEOF(char character) {
        boolean result = false;
        if(character == '\0') {
            result = true;
        }
        return result;
    }

    public boolean checkSpecialTokenCondition(char character) {
        boolean result = false;
        if(isOneCharToken(makeString(character))) {
            result = true;
        }
        return result;
    }

    public boolean checkWhitespaceCondition(char character) {
        boolean result = false;
        if(Character.isWhitespace(character) && character != '\n') {
            result = true;
        }
        return result;
    }

    public boolean checkSingleQuoteStringCondition(char character) {
        boolean result = false;
        if(context.currentCharacter == '\'' && context.previousCharacter != '\\') {
            result = true;
        }
        return result;
    }

    public boolean checkDoubleQuoteStringCondition(char character) {
        boolean result = false;
        if(context.currentCharacter == '\"' && context.previousCharacter != '\\') {
            result = true;
        }
        return result;
    }

    public boolean checkSingleLineCommentCondition(char character) {
        boolean result = false;
        if(context.previousCharacter == '/' && character == '/') {
            result = true;
        }
        return result;
    }

    public boolean checkMultiLineCommentCondition(char character) {
        boolean result = false;
        if(context.previousCharacter == '/' && character == '*') {
            result = true;
        }
        return result;
    }

    public boolean checkSingleLineSQLCommentCondition(char character) {
        boolean result = false;
        if(context.previousCharacter == '-' && character == '-') {
            result = true;
        }
        return result;
    }

    public boolean checkAlphaNumeric(char character) {
        boolean result = false;
        if(isEnglishLetter(character) || character == '_' || Character.isDigit(character)) {
            result = true;
        }
        return result;
    }

    public TokenizerState nextState() {
        if(terminationCondition()) {
            logger.debug("Encountered Termination condition.");
            return null;
        }
        char chNext = context.getNext();
        context.previousCharacter = context.currentCharacter;
        context.currentCharacter = chNext;
        if(checkEOF(chNext)) {
            context.inputStream.close();
            logger.debug("Encountered End Of File condition.");
            return context.endState;
            // reached end of file. Clearing the input steam
            // will allow Tokenizer to switch Termination condition.
        }
        if(checkSingleLineSQLCommentCondition(chNext) && context.enableSQLParsing) {
            logger.debug("Encountered Single Line SQL Comment condition.");
            return context.singleLineSQLCommentState;
        }
        if(checkSpecialTokenCondition(chNext)) {
            logger.debug("Encountered Special Token condition.");
            // return special token state.
            return context.specialTokenState;
        }
        if(checkWhitespaceCondition(chNext)) {
            logger.debug("Encountered Whitespace condition.");
            // return whitespace token state.
            return context.whitespaceState;
        }
        if(checkSingleLineCommentCondition(chNext)) {
            logger.debug("Encountered Single Line Comment condition.");
            // return comment state.
            return context.singleLineCommentState;
        }
        if(checkMultiLineCommentCondition(chNext)) {
            logger.debug("Encountered Multi Line Comment condition.");
            // return comment state.
            return context.multiLineCommentState;
        }
        if(Character.isDigit(chNext)) {
            logger.debug("Encountered Numeric condition.");
            return context.numericState;
        }
        if(checkAlphaNumeric(chNext)) {
            logger.debug("Encountered Alphanumeric condition.");
            // return alphabetic token state.
            return context.alphaNumericState;
        }
        if(checkSingleQuoteStringCondition(chNext)) {
            logger.debug("Encountered String Value condition.");
            // return Single quote string state.
            return context.singleQuoteStringState;
        }
        if(checkDoubleQuoteStringCondition(chNext)) {
            logger.debug("Encountered String Value condition.");
            // return Double quote string state.
            return context.doubleQuoteStringState;
        }
        if(checkEOF(context.peekNext())) {
            logger.debug("Will encounter End Of File condition next.");
            // return whitespace token state.
            return context.whitespaceState;
        }
        logger.error("Could not place Tokenizer into a valid state.");
        return context.endState;
    }
}
