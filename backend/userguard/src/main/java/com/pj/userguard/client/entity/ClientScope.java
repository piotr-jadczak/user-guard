package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.Scope;
import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "client_scopes")
public class ClientScope extends BasicEntity {

    @Column(name = "scope", nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;

    protected ClientScope() {}

    private ClientScope(Scope scope) {
        this.scope = scope;
    }

    public static ClientScope of(String scopeValue) {
        return new ClientScope(Scope.fromString(scopeValue));
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
