package com.pj.userguard.client.command;

import com.pj.userguard.client.entity.ClientConfiguration;
import com.pj.userguard.client.entity.RedirectUri;
import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.ClientName;
import com.pj.userguard.client.field.ClientSecret;
import com.pj.userguard.client.field.UniqueId;
import com.pj.userguard.util.jpa.field.Uri;
import com.pj.userguard.util.lang.AssertCollection;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record CreateClientCommand(
        UniqueId uniqueId,
        ClientId clientId,
        ClientSecret clientSecret,
        ClientName clientName,
        Set<RedirectUri> redirectUris,
        ClientConfiguration configuration) {

    public static CreateClientCommand of(RegisteredClient client, PasswordEncoder encoder,
                                         ClientConfiguration configuration) {
        return new CreateClientCommand(
                UniqueId.of(client.getId()),
                ClientId.from(client.getClientId(), client.getClientIdIssuedAt()),
                ClientSecret.fromString(client.getClientSecret(), encoder),
                ClientName.of(client.getClientName()),
                mapToRedirectUris(client.getRedirectUris(), client.getPostLogoutRedirectUris()),
                configuration);
    }

    private static Set<RedirectUri> mapToRedirectUris(Set<String> redirectUris, Set<String> postLogoutRedirectUris) {
        AssertCollection.notEmpty(redirectUris, "no redirect uris");

        return Stream.of(redirectUris.stream().map(Uri::of).map(RedirectUri::postLoginUri).toList(),
                postLogoutRedirectUris.stream().map(Uri::of).map(RedirectUri::postLogoutUri).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }
}
