package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.AuthGrantType;
import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Getter
@Entity
@Table(name = "client_auth_grant_types")
public class ClientAuthGrantType extends BasicEntity {

    @Column(name = "auth_grant_type")
    @Enumerated(EnumType.STRING)
    private AuthGrantType authGrantType;

    protected ClientAuthGrantType() {}

    private ClientAuthGrantType(AuthGrantType authGrantType) {
        this.authGrantType = authGrantType;
    }

    public static ClientAuthGrantType of(AuthGrantType authGrantType) {
        return new ClientAuthGrantType(authGrantType);
    }

    public static ClientAuthGrantType of(AuthorizationGrantType type) {
        return new ClientAuthGrantType(AuthGrantType.fromAuthorizationGrantType(type));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ClientAuthGrantType that = (ClientAuthGrantType) o;

        return new EqualsBuilder()
                .append(authGrantType, that.authGrantType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(authGrantType)
                .toHashCode();
    }
}
