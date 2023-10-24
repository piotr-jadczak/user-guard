package com.pj.userguard.client.dto;

import com.pj.userguard.client.entity.Client;
import com.pj.userguard.client.entity.RedirectUri;
import com.pj.userguard.client.field.RedirectUriType;
import com.pj.userguard.util.jpa.field.Uri;

import java.util.Set;
import java.util.stream.Collectors;

public record CreatedClientDTO(
        String id,
        String clientId,
        String clientSecret,
        String clientName,
        Set<String> postLoginRedirectUris,
        Set<String> postLogoutRedirectUris) {

    public static CreatedClientDTO from(Client client) {
        return new CreatedClientDTO(
                client.getUniqueId().getUniqueId(),
                client.getClientId().getClientId(),
                client.getClientSecret().getClientSecret(),
                client.getClientName().getClientName(),
                mapPostLoginRedirectUris(client.getRedirectUris()),
                mapPostLogoutRedirectUris(client.getRedirectUris())
        );
    }

    private static Set<String> mapPostLoginRedirectUris(Set<RedirectUri> redirectUris) {
        return redirectUris.stream()
                .filter(redirectUri -> redirectUri.getType() == RedirectUriType.POST_LOGIN)
                .map(RedirectUri::getUri)
                .map(Uri::getUri)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static Set<String> mapPostLogoutRedirectUris(Set<RedirectUri> redirectUris) {
        return redirectUris.stream()
                .filter(redirectUri -> redirectUri.getType() == RedirectUriType.POST_LOGIN)
                .map(RedirectUri::getUri)
                .map(Uri::getUri)
                .collect(Collectors.toUnmodifiableSet());
    }
}
