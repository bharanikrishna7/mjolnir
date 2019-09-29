package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class MultiLineCommentState extends TokenizerState {
    public MultiLineCommentState(TokenizerContext _context) {
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
        } while(!(context.currentCharacter == '/' && context.previousCharacter == '*'));
    }
}
