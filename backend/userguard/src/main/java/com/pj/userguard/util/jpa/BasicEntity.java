package com.pj.userguard.util.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@MappedSuperclass
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    @SuppressWarnings("unchecked")
    protected final <T extends BasicEntity> boolean entityEquals(Object entity, Class<T> entityClazz) {
        if (this == entity) return true;

        if (entity == null || getClass() != entityClazz) return false;

        T that = (T) entity;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    protected final int entityHashcode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
