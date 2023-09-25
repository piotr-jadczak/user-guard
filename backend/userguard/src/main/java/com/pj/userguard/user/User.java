package com.pj.userguard.user;

import com.pj.userguard.user.command.CreateUserCommand;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Password;
import com.pj.userguard.user.field.Username;
import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

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

    protected User() {
        this.username = null;
        this.password = null;
        this.emailAddress = null;
    }

    private User(Username username, Password password, EmailAddress emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    private static User createUser(CreateUserCommand command) {
        return new User(command.username(), command.password(), command.emailAddress());
    }
}
