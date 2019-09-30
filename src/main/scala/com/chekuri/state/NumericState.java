package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class NumericState extends TokenizerState {
    public NumericState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        context.token = "";
        while(Character.isDigit(context.peekNext())) {
            context.token += context.currentCharacter;
            if(!collectChar()) {
                return;
            }
        }
        context.token += context.currentCharacter;
    }
}
