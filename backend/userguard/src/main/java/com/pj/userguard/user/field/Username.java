package com.pj.userguard.user.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@Embeddable
public class Username {

    /**
     * username can contain only letters (bot uppercase and lowercase), numbers, underscores, hyphens
     * must be between 4 and 32 characters long
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])[A-Za-z0-9_-]{4,32}$");

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    protected Username() {
        this.username = null;
    }

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {
        AssertString.notFollowsPattern(username, USERNAME_PATTERN, "username is invalid");
        return new Username(username);
    }

}
