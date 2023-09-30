package com.pj.userguard.user.exception;

import com.pj.userguard.user.entity.RoleName;

public class RoleNotFoundException extends IllegalArgumentException {

    public RoleNotFoundException(RoleName notFoundRole) {
        super(String.format("Role: %s not found", notFoundRole));
    }
}
