package com.verdeemcasa.api.Domain.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserService userService;
    private final TechnicianService technicianService;

    public AuthorizationService(UserService userService, TechnicianService technicianService) {
        this.userService = userService;
        this.technicianService = technicianService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userService.getByEmail(username);
        if (user != null) {
            return user;
        }
        UserDetails technician = technicianService.getByEmail(username);
        if (technician != null) {
            return technician;
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
