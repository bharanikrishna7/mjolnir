package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class BeginState extends TokenizerState {
    public BeginState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        context.token = " ";
        context.currentCharacter = context.peekNext();
        context.previousCharacter = ' ';
        logger.info("Started Tokenizer.");
    }
}
