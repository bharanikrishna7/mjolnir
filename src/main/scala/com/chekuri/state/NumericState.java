package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class NumericState extends TokenizerState {
    public NumericState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        context.token = "";
        do {
            context.token += context.currentCharacter;
            if(!collectChar()) {
                return;
            }
        } while (Character.isDigit(context.currentCharacter));
        logger.debug("Generated Token -- " + context.token);
    }
}
