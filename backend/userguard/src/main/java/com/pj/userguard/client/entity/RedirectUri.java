package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.RedirectUriType;
import com.pj.userguard.client.field.Uri;
import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Entity
@Table(name = "redirect_uris")
public class RedirectUri extends BasicEntity {

    @Embedded
    private Uri uri;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RedirectUriType type;

    protected RedirectUri() {}

    private RedirectUri(Uri uri, RedirectUriType type) {
        this.uri = uri;
        this.type = type;
    }

    public static RedirectUri postLoginUri(Uri uri) {
        return new RedirectUri(uri, RedirectUriType.POST_LOGIN);
    }

    public static RedirectUri postLogoutUri(Uri uri) {
        return new RedirectUri(uri, RedirectUriType.POST_LOGOUT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RedirectUri that = (RedirectUri) o;

        return new EqualsBuilder()
                .append(uri, that.uri)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uri)
                .append(type)
                .toHashCode();
    }
}
