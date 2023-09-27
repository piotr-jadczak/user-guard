package com.pj.userguard.user.entity;

import com.pj.userguard.util.jpa.BasicEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "name", callSuper = false)
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
}
