package com.example.demo.model.dto;

import java.time.LocalDateTime;

public record LoginResponseDTO(
    String token,
    String tokenType,
    LocalDateTime expiresAt,
    String username
) {
}
