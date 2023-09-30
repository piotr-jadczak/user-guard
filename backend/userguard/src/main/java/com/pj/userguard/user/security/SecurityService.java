package com.pj.userguard.user.security;

import com.pj.userguard.user.repository.UserFinder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
@Transactional(readOnly = true)
public class SecurityService implements UserDetailsService {

    private final UserFinder userFinder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userFinder.findByUsername(username)
                .map(SecurityUser::of)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username: %s not found", username)));
    }
}
