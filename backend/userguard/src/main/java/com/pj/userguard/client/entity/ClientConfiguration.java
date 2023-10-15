package com.pj.userguard.client.entity;

import com.pj.userguard.client.command.CreateClientConfigurationCommand;
import com.pj.userguard.client.field.ClientAuthSettings;
import com.pj.userguard.client.field.TokenAuthSettings;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "client_configurations")
public class ClientConfiguration extends AuditableEntity {

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_auth_methods",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_auth_method_id"))
    private final Set<ClientAuthMethod> authMethods;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_auth_grant_types",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_auth_grant_type_id"))
    private final Set<ClientAuthGrantType> authGrantTypes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_scopes",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_scope_id"))
    private final Set<ClientScope> scopes;

    @Embedded
    private ClientAuthSettings clientAuthSettings;

    @Embedded
    private TokenAuthSettings tokenAuthSettings;

    protected ClientConfiguration() {
        this.authMethods = new HashSet<>();
        this.authGrantTypes = new HashSet<>();
        this.scopes = new HashSet<>();
    }

    private ClientConfiguration(Set<ClientAuthMethod> authMethods, Set<ClientAuthGrantType> authGrantTypes,
                                Set<ClientScope> scopes, ClientAuthSettings clientAuthSettings,
                                TokenAuthSettings tokenAuthSettings) {
        this.authMethods = Set.copyOf(authMethods);
        this.authGrantTypes = Set.copyOf(authGrantTypes);
        this.scopes = Set.copyOf(scopes);
        this.clientAuthSettings = clientAuthSettings;
        this.tokenAuthSettings = tokenAuthSettings;
    }

    public static ClientConfiguration create(CreateClientConfigurationCommand command) {
        return new ClientConfiguration(command.authMethods(),
                command.authGrantTypes(),
                command.scopes(),
                command.clientAuthSettings(),
                command.tokenAuthSettings());
    }

    protected Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
        return authMethods.stream()
                .map(clientAuthMethod -> clientAuthMethod.getMethod().toClientAuthMethod())
                .collect(Collectors.toUnmodifiableSet());
    }

    protected Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
        return authGrantTypes.stream()
                .map(clientAuthGrantType -> clientAuthGrantType.getAuthGrantType().toAuthGranType())
                .collect(Collectors.toUnmodifiableSet());
    }

    protected Set<String> getScopes() {
        return scopes.stream()
                .map(scope -> scope.getScope().getValue())
                .collect(Collectors.toUnmodifiableSet());
    }

    protected ClientSettings getClientSettings() {
        return clientAuthSettings.toClientSettings();
    }

    protected TokenSettings getTokenSettings() {
        return tokenAuthSettings.toTokenSettings();
    }
}
