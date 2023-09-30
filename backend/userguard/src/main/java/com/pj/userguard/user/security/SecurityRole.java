package com.pj.userguard.user.security;

import com.pj.userguard.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(of = "role")
@AllArgsConstructor(staticName = "of")
public class SecurityRole implements GrantedAuthority {

    private final static String PREFIX = "ROLE_";

    private final Role role;

    @Override
    public String getAuthority() {
        return PREFIX + role.getName();
    }
}
