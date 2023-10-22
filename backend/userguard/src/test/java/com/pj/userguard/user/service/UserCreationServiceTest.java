package com.pj.userguard.user.service;

import com.pj.userguard.user.entity.RoleName;
import com.pj.userguard.user.entity.User;
import com.pj.userguard.user.exception.RoleNotFoundException;
import com.pj.userguard.user.exception.UserWithEmailAddressAlreadyExistsException;
import com.pj.userguard.user.exception.UserWithUsernameAlreadyExistsException;
import com.pj.userguard.user.repository.RoleRepository;
import com.pj.userguard.user.repository.UserFinder;
import com.pj.userguard.user.repository.UserRepository;
import com.pj.userguard.util.AssertionsUtils;
import com.pj.userguard.util.DataUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.pj.userguard.user.UserTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCreationServiceTest {

    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private UserFinder userFinderMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private UserCreationService userCreationService;


    @Test
    void createUser_throwsException_roleUserNotFound() {
        when(roleRepositoryMock.getByRoleName(RoleName.USER)).thenReturn(Optional.empty());

        AssertionsUtils.assertThrowsWithMessage(RoleNotFoundException.class,
                () -> userCreationService.createUser(defaultCreateUserDTO()),
                String.format("Role: %s not found", RoleName.USER));
    }

    @Test
    void createUser_throwsException_userWithUsernameAlreadyExists() {
        defaultMockRoleRepository();
        defaultMockPasswordEncoder();
        when(userFinderMock.findByUsername(USERNAME_OBJECT)).thenReturn(Optional.of(USERNAME_OBJECT));

        AssertionsUtils.assertThrowsWithMessage(UserWithUsernameAlreadyExistsException.class,
                () -> userCreationService.createUser(defaultCreateUserDTO()),
                String.format("User with user: %s already exists", USERNAME));
    }

    @Test
    void createUser_throwsException_userWithEmailAddressAlreadyExists() {
        defaultMockRoleRepository();
        defaultMockPasswordEncoder();
        defaultMockUsernameValidation();
        when(userFinderMock.findByEmailAddress(EMAIL_ADDRESS)).thenReturn(Optional.of(EMAIL_ADDRESS));

        AssertionsUtils.assertThrowsWithMessage(UserWithEmailAddressAlreadyExistsException.class,
                () -> userCreationService.createUser(defaultCreateUserDTO()),
                String.format("User with email: %s already exists", EMAIL));
    }

    @Test
    void createUser_success_createsUser() {
        defaultMockRoleRepository();
        defaultMockPasswordEncoder();
        defaultMockUsernameValidation();
        defaultMockEmailAddressValidation();
        defaultMockUserRepository();

        var createdUserDTO = userCreationService.createUser(defaultCreateUserDTO());

        verify(userRepositoryMock, times(1)).save(any(User.class));

        assertThat(createdUserDTO).isNotNull();
        assertThat(createdUserDTO.username()).isEqualTo(USERNAME);
        assertThat(createdUserDTO.email()).isEqualTo(EMAIL);
        assertThat(createdUserDTO.roleNames()).isEqualTo(List.of(RoleName.USER));
    }

    private void defaultMockRoleRepository() {
        when(roleRepositoryMock.getByRoleName(RoleName.USER)).thenReturn(Optional.of(USER_ROLE));
    }

    private void defaultMockPasswordEncoder() {
        when(passwordEncoderMock.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
    }

    private void defaultMockUsernameValidation() {
        when(userFinderMock.findByUsername(USERNAME_OBJECT)).thenReturn(Optional.empty());
    }

    private void defaultMockEmailAddressValidation() {
        when(userFinderMock.findByEmailAddress(EMAIL_ADDRESS)).thenReturn(Optional.empty());
    }

    private void defaultMockUserRepository() {
        when(userRepositoryMock.save(any(User.class))).thenAnswer(invocation -> {
            var user = invocation.getArgument(0, User.class);
            DataUtils.setId(user, User.class, USER_ID);
            return user;
        });
    }
}
