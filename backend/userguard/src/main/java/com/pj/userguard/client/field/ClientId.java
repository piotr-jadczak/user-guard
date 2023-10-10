package com.pj.userguard.client.field;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
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

    private ClientId(String clientId) {
        this.clientId = clientId;
        this.issuedAt = LocalDateTime.now();
    }

    public static ClientId createRandom() {
        return new ClientId(UUID.randomUUID().toString());
    }

    public static ClientId fromUUID(UUID uuid) {
        Objects.requireNonNull(uuid, "uuid is null");
        return new ClientId(uuid.toString());
    }
}
