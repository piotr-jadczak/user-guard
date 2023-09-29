package com.pj.userguard.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleName {
    USER,
    ADMIN;

    public static RoleName[] SUPER_USER_ROLES = RoleName.values();
}
