package org.example;

public class StringVerifier {

    public static boolean verifyString(String input) {
        if (input.length() != 9) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (c != 'w' && c != 'b' && c != 'g' && c != 'r' && c != 'y' && c != 'o') {
                return false;
            }
        }

        return true;
    }
}