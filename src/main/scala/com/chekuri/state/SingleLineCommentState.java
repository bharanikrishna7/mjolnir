package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class SingleLineCommentState extends TokenizerState {
    public SingleLineCommentState(TokenizerContext _context) {
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
        } while(context.currentCharacter != '\n');
    }
}
