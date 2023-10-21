package com.pj.userguard.client.entity;

import com.pj.userguard.client.field.RedirectUriType;
import com.pj.userguard.util.jpa.BasicEntity;
import com.pj.userguard.util.jpa.field.Uri;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "redirect_uris")
public class RedirectUri extends BasicEntity {

    @Embedded
    private Uri uri;

    @Column(name = "type", nullable = false)
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
        return entityEquals(this, RedirectUri.class);
    }

    @Override
    public int hashCode() {
        return entityHashcode();
    }
}
