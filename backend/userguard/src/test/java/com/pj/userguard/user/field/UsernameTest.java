package com.pj.userguard.user.field;

import com.pj.userguard.util.ArgumentsUtils;
import com.pj.userguard.util.AssertionsUtils;
import com.pj.userguard.util.lang.CollectionsUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UsernameTest {

    private static Stream<Arguments> invalidUsernames() {
        var invalidUsernames = CollectionsUtils.unmodifiableCollection(null, "", " ", "da", "danny%%",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1234", "ABBA!!", "john kowalski");
        return ArgumentsUtils.createArgumentsWithValues(invalidUsernames);
    }

    @ParameterizedTest(name = "{index} => user=''{0}''")
    @MethodSource("invalidUsernames")
    void of_throwsException(String username) {

        AssertionsUtils.assertThrowsWithMessage(IllegalArgumentException.class,
                () -> Username.of(username),
                "username is invalid");
    }

    private static Stream<Arguments> validUsernames() {
        var validUsernames = CollectionsUtils.unmodifiableCollection("john_kowalski", "johnny123",
                "john-kowalski", "bzzz");
        return ArgumentsUtils.createArgumentsWithValues(validUsernames);
    }

    @ParameterizedTest(name = "{index} => user=''{0}''")
    @MethodSource("validUsernames")
    void of_validUsername(String username) {

        var usernameObject = Username.of(username);

        assertThat(usernameObject.getUsername()).isEqualTo(username);
    }
}
