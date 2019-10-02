package com.chekuri.utilities;

import java.util.Arrays;
import java.util.HashSet;

public final class StringUtilities {
    public static boolean charIsPunctuator(char character) {
        HashSet<Character> punctuators = new HashSet<Character>(
                Arrays.asList('\'', ':', ',', '-', '!', '_', '(', ')', '.', '?', '\"', ';')
        );
        return punctuators.contains(character);
    }

    public static boolean isEscaped(char value) {
        boolean result = false;
        if(value == '\\') {
            result = true;
        }
        return result;
    }

    public static boolean isEnglishLetter(char character) {
        boolean result = false;
        if((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
            result = true;
        }
        return result;
    }
}
