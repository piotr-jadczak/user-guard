package com.pj.userguard.util;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertionsUtils {

    private AssertionsUtils() {}

    public static <T extends Throwable> void assertThrowsWithMessage(Class<T> expectedType, Executable executable,
                                                                     String expectedMessage) {
        T throwable = assertThrows(expectedType, executable);
        assertEquals(expectedMessage, throwable.getMessage());
    }
}
