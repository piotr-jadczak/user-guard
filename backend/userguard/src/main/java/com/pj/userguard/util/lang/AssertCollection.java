package com.pj.userguard.util.lang;

import java.util.Collection;

public class AssertCollection {

    private AssertCollection() {}

    public static <T> void notNull(Collection<T> collection, String message) {
        if (collection == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void notNull(Collection<T> collection) {
        notNull(collection, AssertCollection.ERROR.NULL_COLLECTION.message);
    }

    public static <T> void notEmpty(Collection<T> collection, String message) {
        notNull(collection);
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private enum ERROR {
        NULL_COLLECTION("collection is null");

        private final String message;

        ERROR(String message) {
            this.message = message;
        }
    }
}
