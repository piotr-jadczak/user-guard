package com.pj.userguard.util.lang;

import java.util.Objects;
import java.util.regex.Pattern;

public class AssertString {

    private AssertString() {}

    public static void notNull(String s, String message) {
        if (s == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(String s) {
        notNull(s, ERROR.NULL_STRING.message);
    }

    public static void notEmpty(String s, String message) {
        notNull(s);
        if (s.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String s) {
        notEmpty(s, ERROR.EMPTY_STRING.message);
    }

    public static void notBlank(String s, String message) {
        notNull(s);
        if (s.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String s) {
        notBlank(s, ERROR.BLANK_STRING.message);
    }

    public static void followsPattern(String s, Pattern pattern, String message) {
        Objects.requireNonNull(pattern, ERROR.PATTERN_IS_NULL.message);
        notNull(s, message);
        if (pattern.asPredicate().negate().test(s)) {
            throw new IllegalArgumentException(message);
        }
    }

    private enum ERROR {
        NULL_STRING("string is null"),
        EMPTY_STRING("string is empty"),
        BLANK_STRING("string is blank"),
        PATTERN_IS_NULL("pattern is null");

        private final String message;

        ERROR(String message) {
            this.message = message;
        }
    }
}
