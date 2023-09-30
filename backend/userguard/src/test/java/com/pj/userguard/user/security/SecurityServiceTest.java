package com.pj.userguard.user.security;

import com.pj.userguard.user.repository.UserFinder;
import com.pj.userguard.util.AssertionsUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.pj.userguard.user.UserTestUtils.USERNAME;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {

    @Mock
    private UserFinder userFinderMock;

    @InjectMocks
    private SecurityService securityService;

    @Test
    void loadUserByUsername_throwsException_userWithUsernameNotFound() {
        when(userFinderMock.findByUsername(USERNAME)).thenReturn(Optional.empty());

        AssertionsUtils.assertThrowsWithMessage(UsernameNotFoundException.class,
                () -> securityService.loadUserByUsername(USERNAME),
                String.format("User with username: %s not found", USERNAME));
    }
}
