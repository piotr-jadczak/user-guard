package com.pj.userguard.user.command;

import com.pj.userguard.user.dto.CreateUserDTO;
import com.pj.userguard.user.entity.Role;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Password;
import com.pj.userguard.user.field.Username;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public record CreateUserCommand(
        Username username,
        Password password,
        EmailAddress emailAddress,
        Set<Role> roles) {

    public static CreateUserCommand of(CreateUserDTO dto, PasswordEncoder encoder, Role... roles) {
        return new CreateUserCommand(
                Username.of(dto.username()),
                Password.createEncodedPassword(dto.password(), encoder),
                EmailAddress.of(dto.email()),
                Set.of(roles));
    }
}
