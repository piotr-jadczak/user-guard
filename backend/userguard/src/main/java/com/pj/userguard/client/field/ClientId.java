package com.pj.userguard.client.field;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "clientId")
@Embeddable
public class ClientId {

    @Column(name = "client_id", unique = true, nullable = false)
    private final String clientId;

    @Column(name = "client_id_issued_at", nullable = false)
    private final LocalDateTime issuedAt;

    protected ClientId() {
        this.clientId = null;
        this.issuedAt = null;
    }

    private ClientId(String clientId, LocalDateTime localDateTime) {
        this.clientId = clientId;
        this.issuedAt = localDateTime;
    }

    public static ClientId createRandom() {
        return new ClientId(UUID.randomUUID().toString(), LocalDateTime.now());
    }

    public static ClientId fromUUID(UUID uuid) {
        Objects.requireNonNull(uuid, "uuid is null");
        return new ClientId(uuid.toString(), LocalDateTime.now());
    }

    public static ClientId from(String clientId, Instant issuedAt) {
        Objects.requireNonNull(clientId, "client ID is null");
        return new ClientId(clientId, LocalDateTime.ofInstant(issuedAt, ZoneId.systemDefault()));
    }

    public static ClientId from(String clientId) {
        Objects.requireNonNull(clientId, "client ID is null");
        return new ClientId(clientId, LocalDateTime.now());
    }

    public Instant getIssuedAt() {
        Objects.requireNonNull(issuedAt, "issued at is null");
        return issuedAt.toInstant(ZoneOffset.UTC);
    }
}
