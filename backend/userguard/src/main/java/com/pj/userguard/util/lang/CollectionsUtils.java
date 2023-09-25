package com.pj.userguard.util.lang;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CollectionsUtils {

    private CollectionsUtils() {}

    public static <T> Set<T> newHashSet(T element) {
        return new HashSet<>(Arrays.asList(element));
    }
}
