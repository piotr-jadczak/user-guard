package com.pj.userguard.user.entity;

import com.pj.userguard.user.command.CreateUserCommand;
import com.pj.userguard.user.field.AccountState;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Password;
import com.pj.userguard.user.field.Username;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User extends AuditableEntity {

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private AccountState accountState;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    protected User() {
        this.roles = new HashSet<>();
    }

    private User(Username username, Password password, EmailAddress emailAddress, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.roles = Set.copyOf(roles);
        this.accountState = AccountState.active();
    }

    public static User createUser(CreateUserCommand command) {
        return new User(command.username(), command.password(), command.emailAddress(), command.roles());
    }

    @Override
    public boolean equals(Object o) {
        return entityEquals(this, User.class);
    }

    @Override
    public int hashCode() {
        return entityHashcode();
    }
}
