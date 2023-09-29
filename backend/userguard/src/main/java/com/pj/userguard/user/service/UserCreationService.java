package com.pj.userguard.user.service;

import com.pj.userguard.user.command.CreateUserCommand;
import com.pj.userguard.user.dto.CreateUserDTO;
import com.pj.userguard.user.dto.CreatedUserDTO;
import com.pj.userguard.user.entity.RoleName;
import com.pj.userguard.user.entity.User;
import com.pj.userguard.user.exception.RoleNotFoundException;
import com.pj.userguard.user.exception.UserWithEmailAddressAlreadyExists;
import com.pj.userguard.user.exception.UserWithUsernameAlreadyExists;
import com.pj.userguard.user.repository.RoleRepository;
import com.pj.userguard.user.repository.UserFinder;
import com.pj.userguard.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class UserCreationService {

    private final PasswordEncoder passwordEncoder;
    private final UserFinder userFinder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CreatedUserDTO createUser(CreateUserDTO dto) {
        var userRole = roleRepository.getByRoleName(RoleName.USER)
                .orElseThrow(() -> new RoleNotFoundException(RoleName.USER));

        var command = CreateUserCommand.of(dto, passwordEncoder, userRole);

        validateUsernameUnique(command);
        validateEmailAddressUnique(command);

        var saveduser = userRepository.save(User.createUser(command));

        return new CreatedUserDTO(saveduser);
    }

    private void validateUsernameUnique(CreateUserCommand command) {
        userFinder.findByUsername(command.username())
                .ifPresent(username -> {throw new UserWithUsernameAlreadyExists(username);});
    }

    private void validateEmailAddressUnique(CreateUserCommand command) {
        userFinder.findByEmailAddress(command.emailAddress())
                .ifPresent(emailAddress -> {throw new UserWithEmailAddressAlreadyExists(emailAddress);});
    }
}
