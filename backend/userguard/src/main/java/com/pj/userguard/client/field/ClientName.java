package com.pj.userguard.client.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(of = "clientName")
@Embeddable
public class ClientName {

    /**
     * client name can contain only letters (both uppercase and lowercase), numbers, underscores, hyphens
     * must be between 4 and 32 characters long
     */
    private static final Pattern CLIENT_NAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])[A-Za-z0-9_-]{4,32}$");

    @Column(name = "client_name", nullable = false)
    private final String clientName;

    protected ClientName() {
        this.clientName = null;
    }

    private ClientName(String clientName) {
        this.clientName = clientName;
    }

    public static ClientName of(String clientName) {
        AssertString.followsPattern(clientName, CLIENT_NAME_PATTERN, "client name is invalid");
        return new ClientName(clientName);
    }
}
