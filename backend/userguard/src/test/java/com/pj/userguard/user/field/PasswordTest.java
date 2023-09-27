package com.pj.userguard.user.field;

import com.pj.userguard.util.ArgumentsUtils;
import com.pj.userguard.util.lang.CollectionsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordTest {

    private static final String HASHED_PASSWORD = "hashedPassword";

    @Mock
    private PasswordEncoder passwordEncoderMock;

    private static Stream<Arguments> invalidPasswords() {
        var invalidPasswords = CollectionsUtils.unmodifiableCollection(null, "", " ", "abcd", "ab",
                "aaaaa23213", "doggy2134", "dogggg123?", "DOGGY1234", "aaa23124!!!");
        return ArgumentsUtils.createArgumentsWithValues(invalidPasswords);
    }

    @ParameterizedTest(name = "{index} => password=''{0}''")
    @MethodSource("invalidPasswords")
    void createEncodedPassword_throwsException_invalidPasswords(String password) {

        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> Password.createEncodedPassword(password, passwordEncoderMock),
                "password is invalid");
    }

    @Test
    void createEncodedPassword_throwsException_passwordEncoderIsNull() {

        var validPassword = "whiteDog123!";

        Assertions.assertThrowsExactly(NullPointerException.class,
                () -> Password.createEncodedPassword(validPassword, null),
                "password encoder is null");
    }

    private static Stream<Arguments> validPasswords() {
        var validPasswords = CollectionsUtils.unmodifiableCollection("whiteDog123!", "123abc!!AD", "1234!Aa??");
        return ArgumentsUtils.createArgumentsWithValues(validPasswords);
    }

    @ParameterizedTest(name = "{index} => password=''{0}''")
    @MethodSource("validPasswords")
    void createEncodedPassword_validPasswords(String password) {

        when(passwordEncoderMock.encode(password)).thenReturn(HASHED_PASSWORD);

        var passwordObject = Password.createEncodedPassword(password, passwordEncoderMock);

        assertThat(passwordObject.getPassword()).isEqualTo(HASHED_PASSWORD);
    }
}
