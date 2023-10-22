package com.pj.userguard.user.exception;

import com.pj.userguard.user.field.Username;

public class UserWithUsernameAlreadyExistsException extends IllegalArgumentException {

    public UserWithUsernameAlreadyExistsException(Username username) {
        super(String.format("User with user: %s already exists", username.getUsername()));
    }
}
