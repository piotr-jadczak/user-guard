package com.pj.userguard.client.field;


import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(of = "clientSecret")
@Embeddable
public class ClientSecret {

    /**
     * client secret must be between 16 and 128 characters long
     * must contain non-white space symbols
     */
    private static final Pattern CLIENT_SECRET_PATTERN = Pattern.compile("^(?! )[^ ]{16,128}$\n");

    @Column(name = "client_secret", nullable = false)
    private final String clientSecret;

    @Column(name = "client_secret_expires_at", nullable = false)
    private final LocalDateTime expiresAt;

    protected ClientSecret() {
        this.clientSecret = null;
        this.expiresAt = null;
    }

    private ClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        this.expiresAt = LocalDateTime.MAX;
    }

    public static ClientSecret createRandom(PasswordEncoder encoder) {
        Objects.requireNonNull(encoder, "password encoder is null");
        var randomSecret = UUID.randomUUID().toString();
        return new ClientSecret(encoder.encode(randomSecret));
    }

    public static ClientSecret fromString(String clientSecret, PasswordEncoder encoder) {
        Objects.requireNonNull(encoder, "password encoder is null");
        AssertString.followsPattern(clientSecret, CLIENT_SECRET_PATTERN, "client secret is invalid");
        return new ClientSecret(encoder.encode(clientSecret));
    }

    public Instant getExpiresAt() {
        Objects.requireNonNull(expiresAt, "expires at is null");
        return expiresAt.toInstant(ZoneOffset.UTC);
    }
}
