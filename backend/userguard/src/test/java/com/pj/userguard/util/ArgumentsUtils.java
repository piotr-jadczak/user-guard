package com.pj.userguard.util;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Collection;
import java.util.stream.Stream;

public class ArgumentsUtils {

    private ArgumentsUtils() {}

    public static <T> Stream<Arguments> createArgumentsWithValues(Collection<T> values) {
        return values.stream().map(Arguments::of);
    }
}
