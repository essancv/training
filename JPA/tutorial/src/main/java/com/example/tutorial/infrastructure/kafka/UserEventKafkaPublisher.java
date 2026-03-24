package com.example.tutorial.infrastructure.kafka;

import com.example.tutorial.domain.user.port.UserEventPublisher;
import com.example.tutorial.domain.user.event.*;
import com.example.tutorial.infrastructure.jpa.persistence.FailedUserEventEntity;
import com.example.tutorial.infrastructure.jpa.persistence.FailedUserEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component

public class UserEventKafkaPublisher implements UserEventPublisher {
    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(UserEventKafkaPublisher.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final FailedUserEventRepository failedEventRepo;
    private final ObjectMapper objectMapper;

    @Value("${topic.user-created}")
    private String userCreatedTopic;

    @Value("${topic.user-updated}")
    private String userUpdatedTopic;

    @Value("${topic.user-deleted}")
    private String userDeletedTopic;

    @Value("${kafka.publisher.max-retries}")
    private int maxRetries;

    public UserEventKafkaPublisher(
            KafkaTemplate<String, Object> kafkaTemplate,
            FailedUserEventRepository failedEventRepo,
            ObjectMapper objectMapper
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.failedEventRepo = failedEventRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishUserCreated(UserCreatedEvent event) {
        sendAsync(userCreatedTopic, event.getUserId().getValue().toString(), event, "UserCreatedEvent", 1);
    }

    @Override
    public void publishUserUpdated(UserUpdatedEvent event) {
        sendAsync(userUpdatedTopic, event.getUserId().getValue().toString(), event, "UserUpdatedEvent", 1);
    }

    @Override
    public void publishUserDeleted(UserDeletedEvent event) {
        sendAsync(userDeletedTopic, event.getUserId().getValue().toString(), event, "UserDeletedEvent", 1);
    }

    private void sendWithRetry(String topic, String key, Object event, String eventType) {
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                kafkaTemplate.send(topic, key, event).get(); // bloqueamos solo aquí
                log.info("Evento {} enviado correctamente en intento {}", eventType, attempt + 1);
                return;
            } catch (Exception ex) {
                attempt++;
                log.warn("Error enviando {}. Intento {} de {}", eventType, attempt, maxRetries);
            }
        }

        // Si llegamos aquí → todos los intentos fallaron
        saveToDLQ(event, eventType, "Max retries exceeded");
    }

 private void sendAsync(String topic, String key, Object event, String eventType, int attempt) {

        try {
        
            kafkaTemplate.send(topic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Evento {} enviado correctamente en intento {}", eventType, attempt);
                    } else {
                        log.warn("Error enviando {} en intento {} de {}: {}",
                                eventType, attempt, maxRetries, ex.getMessage());

                        if (attempt < maxRetries) {
                            sendAsync(topic, key, event, eventType, attempt + 1);
                        } else {
                            saveToDLQ(event, eventType, ex.getMessage());
                        }
                    }
                });
        } catch (Exception ex) {
            log.error("Error inesperado al enviar {}: {}", eventType, ex.getMessage());
            saveToDLQ(event, eventType, "Unexpected error: " + ex.getMessage());
        }
    }

    private void saveToDLQ(Object event, String eventType, String errorMessage) {
        try {
            String json = objectMapper.writeValueAsString(event);

            FailedUserEventEntity failed = new FailedUserEventEntity(eventType, json, errorMessage);

            failedEventRepo.save(failed);

            log.error("Evento {} persistido en DLQ (failed_user_events)", eventType);

        } catch (Exception e) {
            log.error("Error guardando evento fallido en BBDD", e);
        }
    }
}
