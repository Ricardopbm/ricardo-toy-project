package com.example.demo.model.dto;

import java.util.List;

public record NotificationDTO(
    String recipient,
    String title,
    String body,
    List<String> media
) {
}
