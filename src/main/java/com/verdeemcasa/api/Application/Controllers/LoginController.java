package com.verdeemcasa.api.Application.Controllers;

import com.verdeemcasa.api.Application.DTOs.LoginDto;
import com.verdeemcasa.api.Infra.Security.TokenJwt;
import com.verdeemcasa.api.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto data){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(token);
        
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String tokenString = tokenService.generateToken(userDetails);
        TokenJwt JWT = new TokenJwt(tokenString);
        
        return ResponseEntity.ok(JWT);
    }
}
