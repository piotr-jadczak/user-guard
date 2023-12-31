package com.pj.userguard.user.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(of = "email")
@Embeddable
public class EmailAddress {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+){3,128}$");

    @Column(name = "email", nullable = false, unique = true)
    private final String email;

    protected EmailAddress() {
        this.email = null;
    }

    private EmailAddress(String email) {
        this.email = email;
    }

    public static EmailAddress of(String email) {
        AssertString.followsPattern(email, EMAIL_PATTERN, "email address is invalid");
        return new EmailAddress(email);
    }
}
