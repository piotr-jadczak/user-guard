package com.pj.userguard.user.field;

import com.pj.userguard.util.ArgumentsUtils;
import com.pj.userguard.util.AssertionsUtils;
import com.pj.userguard.util.lang.CollectionUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAddressTest {

    private static Stream<Arguments> invalidEmails() {
        var invalidEmails = CollectionUtils.unmodifiableCollection(null, "", " ", "kowalski", "john.kowalski",
                "johnny$$@mail.com", "john12@", "john.com", "john - kowalski@mail.eu");
        return ArgumentsUtils.createArgumentsWithValues(invalidEmails);
    }

    @ParameterizedTest(name = "{index} => email=''{0}''")
    @MethodSource("invalidEmails")
    void of_throwsException(String email) {

        AssertionsUtils.assertThrowsWithMessage(IllegalArgumentException.class,
                () -> EmailAddress.of(email),
                "email address is invalid");
    }

    private static Stream<Arguments> validEmails() {
        var validEmails = CollectionUtils.unmodifiableCollection("john.kowalski@mail.com", "bzzz-123@de.com",
                "johnn123@abc.com", "joon_kow@gmail.com");
        return ArgumentsUtils.createArgumentsWithValues(validEmails);
    }

    @ParameterizedTest(name = "{index} => email=''{0}''")
    @MethodSource("validEmails")
    void of_validEmail(String email) {

        var emailAddress = EmailAddress.of(email);

        assertThat(emailAddress.getEmail()).isEqualTo(email);
    }
}
