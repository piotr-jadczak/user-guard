package com.pj.userguard.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleName {
    USER,
    ADMIN
}
