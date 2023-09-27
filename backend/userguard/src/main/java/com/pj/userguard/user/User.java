package com.pj.userguard.user;

import com.pj.userguard.user.command.CreateUserCommand;
import com.pj.userguard.user.entity.Role;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Password;
import com.pj.userguard.user.field.Username;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    protected User() {}

    private User(Username username, Password password, EmailAddress emailAddress, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.roles = Set.copyOf(roles);
    }

    private static User createUser(CreateUserCommand command) {
        return new User(command.username(), command.password(), command.emailAddress(), command.roles());
    }
}
