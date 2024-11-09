package com.dsd.rfoodsp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.config.Security.AuthResponse;
import com.dsd.rfoodsp.config.Security.LoginRequest;
import com.dsd.rfoodsp.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService servicio;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest datos){

        return ResponseEntity.ok(
            servicio.login(datos)
        );
    }
    
}
