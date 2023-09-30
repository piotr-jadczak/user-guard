package com.pj.userguard.user.security;

import com.pj.userguard.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor(staticName = "of")
public class SecurityUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(SecurityRole::of)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountState().isNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountState().isNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getAccountState().isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountState().isEnabled();
    }
}
