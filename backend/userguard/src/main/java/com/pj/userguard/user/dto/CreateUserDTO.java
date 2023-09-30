package com.pj.userguard.user.dto;

public record CreateUserDTO(
        String username,
        String password,
        String email) {
}
