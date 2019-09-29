package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class WhitespaceState extends TokenizerState {
    public WhitespaceState(TokenizerContext _context) {
        context = _context;
    }
    @Override
    public void eatChars() {
        context.token = "";
        do {
            context.token = context.token.concat(makeString(context.currentCharacter));
            if(!collectChar()) {
                return;
            }
        } while(checkWhitespaceCondition(context.peekNext()));
    }
}
