package com.pj.userguard.client.command;

import com.pj.userguard.client.entity.ClientAuthGrantType;
import com.pj.userguard.client.entity.ClientAuthMethod;
import com.pj.userguard.client.entity.ClientScope;
import com.pj.userguard.client.field.ClientAuthSettings;
import com.pj.userguard.client.field.TokenAuthSettings;
import com.pj.userguard.util.lang.AssertCollection;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Set;
import java.util.stream.Collectors;

public record CreateClientConfigurationCommand(
        Set<ClientAuthMethod> authMethods,
        Set<ClientAuthGrantType> authGrantTypes,
        Set<ClientScope> scopes,
        ClientAuthSettings clientAuthSettings,
        TokenAuthSettings tokenAuthSettings) {

    public static CreateClientConfigurationCommand of(RegisteredClient client) {
        return new CreateClientConfigurationCommand(
                mapClientAuthMethods(client.getClientAuthenticationMethods()),
                mapAuthGranTypes(client.getAuthorizationGrantTypes()),
                mapScopes(client.getScopes()),
                ClientAuthSettings.fromClientSettings(client.getClientSettings()),
                TokenAuthSettings.fromTokenSettings(client.getTokenSettings()));
    }

    private static Set<ClientAuthMethod> mapClientAuthMethods(Set<ClientAuthenticationMethod> methods) {
        AssertCollection.notEmpty(methods, "no client authentication methods");
        return methods.stream().map(ClientAuthMethod::of).collect(Collectors.toUnmodifiableSet());
    }

    private static Set<ClientAuthGrantType> mapAuthGranTypes(Set<AuthorizationGrantType> types) {
        AssertCollection.notEmpty(types, "no authorization grant types");
        return types.stream().map(ClientAuthGrantType::of).collect(Collectors.toUnmodifiableSet());
    }

    private static Set<ClientScope> mapScopes(Set<String> scopes) {
        AssertCollection.notEmpty(scopes, "no scopes");
        return scopes.stream().map(ClientScope::of).collect(Collectors.toUnmodifiableSet());
    }
}
