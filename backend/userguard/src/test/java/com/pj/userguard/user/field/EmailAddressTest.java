package com.pj.userguard.user.field;

import com.pj.userguard.util.ArgumentsUtils;
import com.pj.userguard.util.lang.CollectionsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class EmailAddressTest {

    private static Stream<Arguments> invalidEmails() {
        var invalidEmails = CollectionsUtils.unmodifiableCollection(null, "", " ", "kowalski", "john.kowalski",
                "johnny$$@mail.com", "john12@", "john.com", "john - kowalski@mail.eu");
        return ArgumentsUtils.createArgumentsWithValues(invalidEmails);
    }

    @ParameterizedTest(name = "{index} => email=''{0}''")
    @MethodSource("invalidEmails")
    void of_throwsException(String email) {

        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> EmailAddress.of(email),
                "email address is invalid");
    }
}
