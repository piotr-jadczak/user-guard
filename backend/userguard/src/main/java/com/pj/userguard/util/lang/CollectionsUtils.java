package com.pj.userguard.util.lang;

import java.util.*;

public class CollectionsUtils {

    private CollectionsUtils() {}

    public static <T> Set<T> newHashSet(T element) {
        return new HashSet<>(Collections.singletonList(element));
    }

    public static <T> Collection<T> unmodifiableCollection(T... elements) {
       return Collections.unmodifiableCollection(Arrays.asList(elements));
    }
}
