package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.ClientAuthSettings;
import com.pj.userguard.client.field.TokenAuthSettings;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name = "client_configurations")
public class ClientConfiguration extends AuditableEntity {

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_auth_methods",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_auth_method_id"))
    private Set<ClientAuthMethod> authMethods;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_auth_grant_types",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_auth_grant_type_id"))
    private Set<ClientAuthGrantType> authGrantTypes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_configurations_scopes",
            joinColumns = @JoinColumn(name = "client_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "client_scope_id"))
    private Set<ClientScope> scopes;

    @Embedded
    private ClientAuthSettings clientAuthSettings;

    @Embedded
    private TokenAuthSettings tokenAuthSettings;

    protected ClientConfiguration() {}

    private ClientConfiguration(Set<ClientAuthMethod> authMethods, Set<ClientAuthGrantType> authGrantTypes,
                                Set<ClientScope> scopes, ClientAuthSettings clientAuthSettings,
                                TokenAuthSettings tokenAuthSettings) {
        this.authMethods = Set.copyOf(authMethods);
        this.authGrantTypes = Set.copyOf(authGrantTypes);
        this.scopes = Set.copyOf(scopes);
        this.clientAuthSettings = clientAuthSettings;
        this.tokenAuthSettings = tokenAuthSettings;
    }
}
