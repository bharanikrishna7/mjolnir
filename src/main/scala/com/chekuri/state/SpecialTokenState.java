package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class SpecialTokenState extends TokenizerState {
    public SpecialTokenState(TokenizerContext _context) {
        context = _context;
    }

    @Override
    public void eatChars() {
        logger.debug("Eating characters associated with Special Token state.");
        context.token = makeString(context.currentCharacter);
        String checkTwoCharTokens = context.token.concat(makeString(context.peekNext()));
        if(isTwoCharToken(checkTwoCharTokens)) {
            logger.debug("Encountered Two Characters Special Token.");
            collectChar();
            context.token = context.token.concat(makeString(context.currentCharacter));
        } else {
            logger.debug("Encountered One Character Special Token.");
        }
    }
}
