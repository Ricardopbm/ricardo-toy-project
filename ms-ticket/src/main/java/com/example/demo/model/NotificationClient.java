package com.example.demo.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.dto.NotificationDTO;

@Component
public class NotificationClient {

    private static final Logger logger = LoggerFactory.getLogger(NotificationClient.class);

    private final RestTemplate rest;
    private final String notificationUrl;

    public NotificationClient(
        RestTemplate rest,
        @Value("${app.notification.url}") String notificationUrl
    ) {
        this.rest = rest;
        this.notificationUrl = notificationUrl;
    }

    public void send(String recipient, String title, String body) {
        try {
            rest.postForEntity(
                notificationUrl,
                new NotificationDTO(recipient, title, body, List.of("mail")),
                Void.class
            );
        } catch (RestClientException ex) {
            logger.warn("Nao foi possivel notificar {}: {}", recipient, ex.getMessage());
        }
    }
}
