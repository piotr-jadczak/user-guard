package com.pj.userguard.user.exception;

import com.pj.userguard.user.field.Username;

public class UserWithUsernameAlreadyExists extends IllegalArgumentException {

    public UserWithUsernameAlreadyExists(Username username) {
        super(String.format("User with username: %s already exists", username.getUsername()));
    }
}
