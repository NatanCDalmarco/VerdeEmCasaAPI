package com.verdeemcasa.api.Domain.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userService.getByEmail(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
