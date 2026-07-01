package com.example.demo.model.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewTicketDTO(
    @NotBlank(message = "A acao deve ser informada")
    String action,

    @NotBlank(message = "O objeto deve ser informado")
    String object,

    @NotBlank(message = "Os detalhes devem ser informados")
    String details,

    @NotBlank(message = "O criador deve ser informado")
    @Email(message = "O criador deve ser um email valido")
    String creator,

    @Email(message = "O destinatario deve ser um email valido")
    String recipient,

    List<@Email(message = "Observador deve ser um email valido") String> observers
) {
}
