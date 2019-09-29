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

    public static boolean isEscapedString(String value) {
        int backSlashCount = 0;
        for(int index = value.length() - 1; index >= 0; index--) {
            if(value.charAt(index) == '\\') {
                backSlashCount++;
            }
        }
        return (backSlashCount % 2 == 0);
    }

    public static boolean isEnglishLetter(char character) {
        boolean result = false;
        if((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
            result = true;
        }
        return result;
    }
}
