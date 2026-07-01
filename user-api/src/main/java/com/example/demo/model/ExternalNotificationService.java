package com.example.demo.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalNotificationService implements INotificationService {

  private static final Logger logger = LoggerFactory.getLogger(ExternalNotificationService.class);

  private final RestTemplate rest;
  private final String notificationUrl;

  public ExternalNotificationService(
    RestTemplate rest,
    @Value("${app.notification.url}") String notificationUrl
  ) {
    this.rest = rest;
    this.notificationUrl = notificationUrl;
  }

  @Override
  public void sendNotification(String destination, String title, String body) {
    logger.info("Chamando servico de notificacao externo em {}", notificationUrl);

    try {
      rest.postForEntity(
        notificationUrl,
        Map.of(
          "recipient", destination,
          "title", title,
          "body", body,
          "media", List.of("mail")
        ),
        Void.class
      );
    } catch (RestClientException ex) {
      logger.warn("Nao foi possivel notificar {}: {}", destination, ex.getMessage());
    }
  }
}
