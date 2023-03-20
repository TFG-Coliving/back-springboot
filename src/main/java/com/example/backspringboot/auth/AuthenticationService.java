package com.example.backspringboot.auth;

import com.example.backspringboot.config.JwtService;
import com.example.backspringboot.model.ImageData;
import com.example.backspringboot.user.Role;
import com.example.backspringboot.user.User;
import com.example.backspringboot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws ParseException {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .phone(request.getPhone())
                .score(0.0)
                .score_hidden(false)
                .bith_date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getBirth_date()))
                .profilePicture(
                        ImageData.builder()
                                .name("default_profile_picture.png")
                                .type("image/png")
                                .uri(File.separator + "static" + File.separator + "images" + File.separator + "default_profile_picture.png")
                                .path(
                                        System.getProperty("user.dir") + File.separator + "src" + File.separator +
                                        "main" + File.separator + "resources" + File.separator + "static" + File.separator +
                                        "images" + File.separator + "default_profile_picture.png"
                                )
                                .build()
                )
                .build();

        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
