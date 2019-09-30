package com.chekuri.state;

import com.chekuri.TokenizerContext;

public class WhitespaceState extends TokenizerState {
    public WhitespaceState(TokenizerContext _context) {
        context = _context;
    }
    @Override
    public void eatChars() {
        context.token = " ";
    }
}
