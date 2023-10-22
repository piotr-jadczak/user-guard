package com.pj.userguard.client.api;

import java.util.Set;

public record CreateDefaultClientDTO(
        String id,
        String clientId,
        String clientSecret,
        String clientName,
        Set<String> postLoginRedirectUris,
        Set<String> postLogoutRedirectUris) {
}
