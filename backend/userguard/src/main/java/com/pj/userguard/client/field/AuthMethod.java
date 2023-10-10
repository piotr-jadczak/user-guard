package com.pj.userguard.client.field;

import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum AuthMethod {
    CLIENT_SECRET_BASIC("client_secret_basic"),
    CLIENT_SECRET_POST("client_secret_post"),
    CLIENT_SECRET_JWT("client_secret_jwt"),
    PRIVATE_KEY_JWT("private_key_jwt"),
    NONE("none");

    private final String value;

    AuthMethod(String value) {
        this.value = value;
    }

    public ClientAuthenticationMethod toClientAuthMethod() {
        return new ClientAuthenticationMethod(this.value);
    }

    public static AuthMethod fromClientAuthenticationMethod(ClientAuthenticationMethod method) {
        for (AuthMethod authMethod : AuthMethod.values()) {
            if (authMethod.value.equals(method.getValue())) {
                return authMethod;
            }
        }
        throw new IllegalArgumentException("No enum match for client authentication method");
    }
}
