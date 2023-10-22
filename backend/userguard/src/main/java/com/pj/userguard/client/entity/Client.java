package com.pj.userguard.client.entity;

import com.pj.userguard.client.command.CreateClientCommand;
import com.pj.userguard.client.field.*;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "clients")
public class Client extends AuditableEntity {

    @Embedded
    private UniqueId uniqueId;

    @Embedded
    private ClientId clientId;

    @Embedded
    private ClientSecret clientSecret;

    @Embedded
    private ClientName clientName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "client_id")
    private final Set<RedirectUri> redirectUris;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "configuration_id")
    private ClientConfiguration configuration;

    protected Client() {
        this.redirectUris = new HashSet<>();
    }

    private Client(UniqueId uniqueId, ClientId clientId, ClientSecret clientSecret, ClientName clientName,
                   Set<RedirectUri> redirectUris, ClientConfiguration configuration) {
        this.uniqueId = uniqueId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.redirectUris = Set.copyOf(redirectUris);
        this.configuration = configuration;
    }

    public static Client createClient(CreateClientCommand command) {
        return new Client(command.uniqueId(),
                command.clientId(),
                command.clientSecret(),
                command.clientName(),
                command.redirectUris(),
                command.configuration());
    }

    public RegisteredClient toRegisteredClient() {
        var builder = RegisteredClient.withId(uniqueId.getUniqueId())
                .clientId(clientId.getClientId())
                .clientIdIssuedAt(clientId.getIssuedAt())
                .clientSecret(clientSecret.getClientSecret())
                .clientSecretExpiresAt(clientSecret.getExpiresAt())
                .clientName(clientName.getClientName())
                .redirectUris(ru -> ru.addAll(getPostLoginRedirectUris()))
                .postLogoutRedirectUris(ru -> ru.addAll(getPostLogoutRedirectUris()))
                .clientAuthenticationMethods(am -> am.addAll(configuration.getClientAuthenticationMethods()))
                .authorizationGrantTypes(ag -> ag.addAll(configuration.getAuthorizationGrantTypes()))
                .clientSettings(configuration.getClientSettings())
                .tokenSettings(configuration.getTokenSettings());

        return builder.build();
    }

    private Set<String> getPostLoginRedirectUris() {
        return redirectUris.stream()
                .filter(redirectUri -> redirectUri.getType() == RedirectUriType.POST_LOGIN)
                .map(redirectUri -> redirectUri.getUri().getUri())
                .collect(Collectors.toUnmodifiableSet());
    }

    private Set<String> getPostLogoutRedirectUris() {
        return redirectUris.stream()
                .filter(redirectUri -> redirectUri.getType() == RedirectUriType.POST_LOGOUT)
                .map(redirectUri -> redirectUri.getUri().getUri())
                .collect(Collectors.toUnmodifiableSet());
    }
}
