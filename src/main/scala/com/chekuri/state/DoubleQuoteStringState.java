package com.chekuri.state;

import com.chekuri.TokenizerContext;
import com.chekuri.utilities.StringUtilities;

public class DoubleQuoteStringState extends TokenizerState {
    public DoubleQuoteStringState(TokenizerContext _context) {
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
        } while (context.currentCharacter != '\"' || StringUtilities.isEscaped(context.previousCharacter));
        context.token += context.currentCharacter;
    }
}
