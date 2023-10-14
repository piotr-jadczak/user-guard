package com.pj.userguard.client.field;

public enum Scope {
    OPENID("openid"),
    PROFILE("profile"),
    EMAIL("email"),
    ADDRESS("address"),
    PHONE("phone");

    private final String value;

    Scope(String value) {
        this.value = value;
    }

    public static Scope fromString(String string) {
        for (Scope scope : Scope.values()) {
            if (scope.value.equals(string)) {
                return scope;
            }
        }
        throw new IllegalArgumentException("No enum match for authorization grant type");
    }

    public String getValue() {
        return value;
    }
}
