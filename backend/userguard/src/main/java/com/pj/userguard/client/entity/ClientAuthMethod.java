package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.AuthMethod;
import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Getter
@Entity
@Table(name = "client_auth_methods")
public class ClientAuthMethod extends BasicEntity {

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthMethod method;

    protected ClientAuthMethod() {}

    private ClientAuthMethod(AuthMethod method) {
        this.method = method;
    }

    public static ClientAuthMethod of(AuthMethod method) {
        return new ClientAuthMethod(method);
    }

    public static ClientAuthMethod of(ClientAuthenticationMethod method) {
        return new ClientAuthMethod(AuthMethod.fromClientAuthenticationMethod(method));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ClientAuthMethod that = (ClientAuthMethod) o;

        return new EqualsBuilder()
                .append(method, that.method)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(method)
                .toHashCode();
    }
}
