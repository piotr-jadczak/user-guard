package com.pj.userguard.user.dto;

import com.pj.userguard.user.entity.Role;
import com.pj.userguard.user.entity.RoleName;
import com.pj.userguard.user.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public record CreatedUserDTO(String username,
                             String email,
                             List<RoleName> roleNames) {

    public CreatedUserDTO(User user) {
        this(user.getUsername().getUsername(), user.getEmailAddress().getEmail(), mapRoles(user.getRoles()));
    }

    private static List<RoleName> mapRoles(Set<Role> roles) {
        return roles == null
                ? Collections.emptyList()
                : roles.stream().map(Role::getName).sorted().toList();
    }
}
