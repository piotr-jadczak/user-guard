package com.pj.userguard.user.entity;

import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

@Getter
@Entity
@Table(name = "roles")
public class Role extends BasicEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleName name;

    protected Role() {}

    private Role(RoleName name) {
        this.name = name;
    }

    public static Role of(RoleName name) {
        Objects.requireNonNull(name, "roleName is null");
        return new Role(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return new EqualsBuilder().append(name, role.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).toHashCode();
    }
}
