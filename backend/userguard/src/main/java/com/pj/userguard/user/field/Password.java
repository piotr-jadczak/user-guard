package com.pj.userguard.user.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Password {

    /**
     * password must be between 8 and 64 characters long
     * must contain at least one upper case English letter and one lower case English letter
     * must contain at least one number and one special character
     */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,64}$");

    @Column(name = "password", nullable = false)
    private final String password;

    protected Password() {
        this.password = null;
    }

    private Password(String password) {
        this.password = password;
    }

    public static Password createEncodedPassword(String password, PasswordEncoder encoder) {
        Objects.requireNonNull(encoder, "password encoder is null");
        AssertString.followsPattern(password, PASSWORD_PATTERN, "password is invalid");
        return new Password(encoder.encode(password));
    }
}
