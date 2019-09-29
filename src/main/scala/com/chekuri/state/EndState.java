package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class EndState extends TokenizerState {
    public EndState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        context.token = "\0";
        logger.info("Parser has reached end of steam.");
    }
}
