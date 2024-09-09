package org.spring.sec.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.sec.config.CustomUserDetailsService;
import org.spring.sec.dto.requestDto.AuthDto;
import org.spring.sec.dto.responseDto.ResponseDto;
import org.spring.sec.entity.Authority;
import org.spring.sec.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ResponseDto> authenticateUser(@RequestBody AuthDto dto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );

            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String jwt = jwtUtils.generateToken(authentication.getName(),authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new ResponseDto("Login success", LocalDateTime.now(),jwt));
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage(),LocalDateTime.now(),null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public String testApi(){
        return "test";
    }
}
