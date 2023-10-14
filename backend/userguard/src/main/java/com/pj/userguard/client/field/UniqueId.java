package com.pj.userguard.client.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "uniqueId")
@Embeddable
public class UniqueId {

    @Column(name = "unique_id", unique = true, nullable = false)
    private final String uniqueId;

    protected UniqueId() {
        this.uniqueId = null;
    }

    private UniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public static UniqueId of(String uniqueId) {
        AssertString.notBlank(uniqueId, "id is blank");
        return new UniqueId(uniqueId);
    }
}
