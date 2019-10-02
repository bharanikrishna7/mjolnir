package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class SingleLineSQLCommentState extends TokenizerState {
    public SingleLineSQLCommentState(TokenizerContext _context) {
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
        } while(context.currentCharacter != '\n');
        if(!context.enableComments) {
            context.token = "";
        }
    }
}
