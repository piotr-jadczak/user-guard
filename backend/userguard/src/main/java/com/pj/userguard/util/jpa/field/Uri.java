package com.pj.userguard.util.jpa.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(of = "uri")
@Embeddable
public class Uri {

    private static final Pattern URI_PATTERN = Pattern.compile("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]" +
            "{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");

    @Column(name = "uri", nullable = false)
    private final String uri;

    protected Uri() {
        this.uri = null;
    }

    private Uri(String uri) {
        this.uri = uri;
    }

    public static Uri of(String uri) {
        AssertString.followsPattern(uri, URI_PATTERN, "uri is invalid");
        return new Uri(uri);
    }
}
