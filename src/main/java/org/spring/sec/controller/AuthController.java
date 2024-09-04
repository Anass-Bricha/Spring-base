package org.spring.sec.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.sec.config.CustomUserDetailsService;
import org.spring.sec.dto.requestDto.AuthDto;
import org.spring.sec.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager , JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthDto dto){
        log.info("Starting login ...");
        try {
            // Authenticate the user using the provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );

            // Generate the JWT using the authenticated user's details
            String jwt = jwtUtils.generateToken(authentication.getName());

            // Optionally, set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return the JWT in the response
            return ResponseEntity.status(200).body(jwt);
        } catch (AuthenticationException e) {
            // Handle invalid credentials
            log.error(e.getMessage());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/test")
    public String testApi(){
        return "test";
    }
}
