package com.pj.userguard.user.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(of = "username")
@Embeddable
public class Username {

    /**
     * user can contain only letters (bot uppercase and lowercase), numbers, underscores, hyphens
     * must be between 4 and 32 characters long
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])[A-Za-z0-9_-]{4,32}$");

    @Column(name = "username", nullable = false, unique = true)
    private final String username;

    protected Username() {
        this.username = null;
    }

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {
        AssertString.notFollowsPattern(username, USERNAME_PATTERN, "user is invalid");
        return new Username(username);
    }
}
