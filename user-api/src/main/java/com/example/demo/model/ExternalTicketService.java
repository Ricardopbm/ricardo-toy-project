package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.repository.entity.User;

@Component
public class ExternalTicketService implements ITicketService {

    private final RestTemplate rest;
    private final String ticketUrl;

    public ExternalTicketService(
        RestTemplate rest,
        @Value("${app.ticket.url}") String ticketUrl
    ) {
        this.rest = rest;
        this.ticketUrl = ticketUrl;
    }

    @Override
    public void createWorkstationInstallationTicket(User user) {
        rest.postForEntity(
            ticketUrl,
            new NewTicketRequest(
                "instalar",
                "workstation",
                "Instalar workstation para o usuario %s (%s).".formatted(user.getHandle(), user.getEmail()),
                user.getEmail(),
                user.getEmail(),
                List.of()
            ),
            Void.class
        );
    }

    private record NewTicketRequest(
        String action,
        String object,
        String details,
        String creator,
        String recipient,
        List<String> observers
    ) {
    }
}
