package com.example.demo.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.AuthToken;
import com.example.demo.repository.entity.User;

@Service
public class AuthService {

    private static final Duration TOKEN_TTL = Duration.ofHours(2);

    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthTokenRepository authTokenRepository, UserRepository userRepository) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String validateToken(String token) {
        return authTokenRepository.findByToken(token)
                .filter(this::isActive)
                .map(authToken -> authToken.getUser().getHandle())
                .orElse(null);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByHandle(loginRequest.username())
                .or(() -> userRepository.findByEmail(loginRequest.username()))
                .filter(foundUser -> passwordEncoder.matches(loginRequest.password(), foundUser.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Usuario ou senha invalidos"));

        LocalDateTime now = LocalDateTime.now();

        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setUser(user);
        authToken.setCreatedAt(now);
        authToken.setExpiresAt(now.plus(TOKEN_TTL));

        authTokenRepository.save(authToken);

        return new LoginResponseDTO(
                authToken.getToken(),
                "Bearer",
                authToken.getExpiresAt(),
                user.getHandle());
    }

    public void logout(String token) {
        if (token == null || token.isBlank()) {
            return;
        }

        authTokenRepository.findByToken(token)
                .filter(authToken -> authToken.getRevokedAt() == null)
                .ifPresent(authToken -> {
                    authToken.setRevokedAt(LocalDateTime.now());
                    authTokenRepository.save(authToken);
                });
    }

    private boolean isActive(AuthToken authToken) {
        return authToken.getRevokedAt() == null
                && authToken.getExpiresAt().isAfter(LocalDateTime.now());
    }
}
