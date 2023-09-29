package com.pj.userguard.user.exception;

import com.pj.userguard.user.entity.RoleName;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(RoleName notFoundRole) {
        super(String.format("Role: %s not found", notFoundRole));
    }
}
