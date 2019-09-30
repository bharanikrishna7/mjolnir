package com.chekuri;

import com.chekuri.floki.io.reader;
import com.chekuri.state.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;

public class TokenizerContext {
    protected Logger logger = LogManager.getLogger(this);

    public String token;
    public reader inputStream;
    public HashSet<String> oneCharTokens;
    public HashSet<String> twoCharTokens;
    public char previousCharacter;
    public char currentCharacter;
    public int lineCount;
    public boolean enableSQLParsing;

    private void defaultInit() {
        previousCharacter = '\0';
        currentCharacter = '\0';
        lineCount = 0;
        oneCharTokens = new HashSet<String>(
                Arrays.asList("\n", "#", "<", ">", "{", "}", "[", "]", "(", ")", ":", "=", "+", "-", "*", ".", ";")
        );
        twoCharTokens = new HashSet<String>(
                Arrays.asList("<<", ">>", "::", "++", "--", "==", "+=", "-=", "*=", "/=", "!=")
        );
        enableSQLParsing = false;
        initializeStates();
        logger.info("Set Initial State to Begin State.");
        currentState = beginState;
    }

    public void initializeStates() {
        logger.info("Initializing Tokenizer States for Tokenizer Context.");
        specialTokenState = new SpecialTokenState(this);
        whitespaceState = new WhitespaceState(this);
        singleQuoteStringState = new SingleQuoteStringState(this);
        doubleQuoteStringState = new DoubleQuoteStringState(this);
        alphaNumericState = new AlphaNumericState(this);
        numericState = new NumericState(this);
        singleLineCommentState = new SingleLineCommentState(this);
        multiLineCommentState = new MultiLineCommentState(this);
        singleLineSQLCommentState = new SingleLineSQLCommentState(this);
        beginState = new BeginState(this);
        endState = new EndState(this);
        logger.info("Initialized all Tokenizer States.");
    }

    public TokenizerContext() {
        defaultInit();
        inputStream = null;
    }

    public TokenizerContext(boolean _enableSQLParsing) {
        enableSQLParsing = _enableSQLParsing;
    }

    public TokenizerContext(reader _inputStream) {
        defaultInit();
        inputStream = _inputStream;
    }

    public TokenizerContext(reader _inputStream, boolean _enableSQLParsing) {
        defaultInit();
        enableSQLParsing = _enableSQLParsing;
        inputStream = _inputStream;
    }

    public char peekNext() {
        return inputStream.peekNext();
    }

    public char getNext() {
        return inputStream.getNext();
    }

    // Add Tokenizer States.
    public TokenizerState currentState;
    public SpecialTokenState specialTokenState;
    public WhitespaceState whitespaceState;
    public SingleQuoteStringState singleQuoteStringState;
    public DoubleQuoteStringState doubleQuoteStringState;
    public AlphaNumericState alphaNumericState;
    public NumericState numericState;
    public SingleLineCommentState singleLineCommentState;
    public MultiLineCommentState multiLineCommentState;
    public SingleLineSQLCommentState singleLineSQLCommentState;
    public BeginState beginState;
    public EndState endState;
}
