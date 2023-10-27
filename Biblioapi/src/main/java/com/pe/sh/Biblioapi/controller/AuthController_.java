/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.JwtAuthResponseDto_;
import com.pe.sh.Biblioapi.dto.LoginDto_;
import com.pe.sh.Biblioapi.dto.RegisterDto_;
import com.pe.sh.Biblioapi.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shmen
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController_ {

    private final AuthenticationService authenticationService;

    public AuthController_(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponseDto_> registerUser(@Valid @RequestBody RegisterDto_ regDto) {
        return ResponseEntity.ok(authenticationService.register(regDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto_> authenticateUser(@Valid @RequestBody LoginDto_ logDto) {
        return ResponseEntity.ok(authenticationService.authenticate(logDto));
    }

}
