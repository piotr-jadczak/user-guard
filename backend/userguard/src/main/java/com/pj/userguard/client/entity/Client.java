package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.ClientName;
import com.pj.userguard.client.field.ClientSecret;
import com.pj.userguard.client.field.UniqueId;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

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
    private Set<RedirectUri> redirectUris;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "client_id")
    private ClientConfiguration configuration;

    protected Client() {}

    private Client(UniqueId uniqueId, ClientId clientId, ClientSecret clientSecret, ClientName clientName,
                   Set<RedirectUri> redirectUris, ClientConfiguration configuration) {
        this.uniqueId = uniqueId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.redirectUris = Set.copyOf(redirectUris);
        this.configuration = configuration;
    }
}
