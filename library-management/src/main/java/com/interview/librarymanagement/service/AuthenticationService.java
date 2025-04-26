package com.interview.librarymanagement.service;

import com.interview.librarymanagement.model.AuthenticationResponse;
import com.interview.librarymanagement.model.User;
import com.interview.librarymanagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register (User request){
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user=repository.save(user);

        String token=jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse Authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user=repository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
