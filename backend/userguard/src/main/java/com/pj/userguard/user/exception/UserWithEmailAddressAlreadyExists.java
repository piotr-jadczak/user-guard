package com.pj.userguard.user.exception;

import com.pj.userguard.user.field.EmailAddress;

public class UserWithEmailAddressAlreadyExists extends RuntimeException {

    public UserWithEmailAddressAlreadyExists(EmailAddress emailAddress) {
        super(String.format("User with email: %s already exists", emailAddress.getEmail()));
    }
}
