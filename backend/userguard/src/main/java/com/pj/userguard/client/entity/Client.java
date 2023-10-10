package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.ClientName;
import com.pj.userguard.client.field.ClientSecret;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name = "clients")
public class Client extends AuditableEntity {

    @Embedded
    private ClientId clientId;

    @Embedded
    private ClientSecret clientSecret;

    @Embedded
    private ClientName clientName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "client_redirect_uri",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "redirect_uri_id"))
    Set<RedirectUri> redirectUris;

    protected Client() {}

    private Client(ClientId clientId, ClientSecret clientSecret, ClientName clientName, Set<RedirectUri> redirectUris) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.redirectUris = redirectUris;
    }
}
