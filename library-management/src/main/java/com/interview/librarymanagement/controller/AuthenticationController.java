package com.interview.librarymanagement.controller;

import com.interview.librarymanagement.model.AuthenticationResponse;
import com.interview.librarymanagement.model.User;
import com.interview.librarymanagement.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity <AuthenticationResponse> login(
            @RequestBody User request
    ){
        return ResponseEntity.ok(authService.Authenticate(request));
    }
}

