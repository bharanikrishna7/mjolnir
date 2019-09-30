package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class AlphaNumericState extends TokenizerState {
    public AlphaNumericState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        context.token = "";
        while(checkAlphaNumeric(context.peekNext())) {
            context.token += context.currentCharacter;
            if(!collectChar()) {
                return;
            }
        }
        context.token += context.currentCharacter;
    }
}
