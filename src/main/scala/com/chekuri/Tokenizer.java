package com.chekuri;

import com.chekuri.floki.io.reader;
import com.chekuri.state.TokenizerState;

import java.util.HashSet;
import java.util.List;

public class Tokenizer {
    protected TokenizerState tokenizerState;
    protected TokenizerContext tokenizerContext;

    public Tokenizer(reader inputStream, boolean enableSQLParsing) {
        tokenizerContext = new TokenizerContext(inputStream, enableSQLParsing);
        tokenizerState = tokenizerContext.beginState;
    }

    public Tokenizer(reader inputStream, boolean enableSQLParsing, boolean enableComments) {
        tokenizerContext = new TokenizerContext(inputStream, enableSQLParsing, enableComments);
        tokenizerState = tokenizerContext.beginState;
    }

    public boolean canRead() {
        // can read file and make tokens
        return tokenizerState.canRead();
    }

    public String getToken() {
        tokenizerState.consumeCharacters();
        return tokenizerState.getToken();
    }

    public int getCurrentLineCount() {
        // retrieve the current line count
        return tokenizerContext.lineCount;
    }

    public void setSpecialOneCharTokens(List<String> _oneCharTokens) {
        tokenizerContext.oneCharTokens = new HashSet<String>(_oneCharTokens);
    }

    public void setSpecialTwoCharTokens(List<String> _twoCharTokens) {
        tokenizerContext.twoCharTokens = new HashSet<String>(_twoCharTokens);
    }
}
