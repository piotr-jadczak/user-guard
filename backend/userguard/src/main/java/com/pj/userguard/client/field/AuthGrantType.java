package com.pj.userguard.client.field;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

public enum AuthGrantType {
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
    DEVICE_CODE("urn:ietf:params:oauth:grant-type:device_code");

    private final String value;

    AuthGrantType(String value) {
        this.value = value;
    }

    public AuthorizationGrantType toAuthGranType() {
        return new AuthorizationGrantType(this.value);
    }

    public static AuthGrantType fromAuthorizationGrantType(AuthorizationGrantType type) {
        for (AuthGrantType authGrantType : AuthGrantType.values()) {
            if (authGrantType.value.equals(type.getValue())) {
                return authGrantType;
            }
        }
        throw new IllegalArgumentException("No enum match for authorization grant type");
    }
}
