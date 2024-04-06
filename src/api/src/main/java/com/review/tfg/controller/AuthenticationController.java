package com.review.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.tfg.dto.auth.request.LoginRequest;
import com.review.tfg.dto.auth.request.SignUpRequest;
import com.review.tfg.dto.auth.response.TokenDTO;
import com.review.tfg.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
	@Autowired
    AuthenticationService authenticationService;
	
    @PostMapping("/signup")
    public ResponseEntity<TokenDTO> signup(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/loginToken")
    public ResponseEntity<TokenDTO> loginToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.updateToken(userDetails));
    }
}
