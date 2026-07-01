package com.example.demo.model.dto;

import com.example.demo.repository.entity.TicketStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateTicketStatusDTO(
    @NotNull(message = "O status deve ser informado")
    TicketStatus status,

    String assignee,
    String motivo
) {
}
