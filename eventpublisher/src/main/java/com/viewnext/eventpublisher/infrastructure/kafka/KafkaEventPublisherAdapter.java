package com.viewnext.eventpublisher.infrastructure.kafka;

import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.springframework.kafka.core.KafkaTemplate;
import java.time.Instant;
/**
 * Adaptador de salida que publica eventos en Kafka usando KafkaTemplate.
 */
public class KafkaEventPublisherAdapter implements EventPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventPublisherLogger logger;

    /**
     * Crea el adaptador de publicación en Kafka.
     *
     * @param kafkaTemplate plantilla de Kafka
     * @param logger        logger dedicado del componente
     */
    public KafkaEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate,
                                      EventPublisherLogger logger) {
        this.kafkaTemplate = kafkaTemplate;
        this.logger = logger;
    }

    @Override
    public void publish(OutboundEvent event) {
        /*
        try {

            logger.info("Enviando mensaje a Kafka topic='%s', intento=%d , a las %s", event.getTopic(), event.getAttempt(), Instant.now());
            kafkaTemplate.send(event.getTopic(), event.getPayloadJson());
        } catch (Exception ex) {
            logger.error(String.format(
                    "No se pudo publicar el evento en topic='%s' a las %s. Motivo: %s",
                    event.getTopic(),
                    Instant.now(),
                    ex.getMessage()
        ), ex);

        }
        */
 kafkaTemplate.send(event.getTopic(), event.getPayloadJson())
        .whenComplete((result, ex) -> {

            if (ex == null) {
                logger.info(String.format(
                        "Evento publicado correctamente en topic='%s' a las %s",
                        event.getTopic(),
                        Instant.now()
                ));
            } else {
                logger.error(String.format(
                        "No se pudo publicar el evento en topic='%s' a las %s. Motivo: %s",
                        event.getTopic(),
                        Instant.now(),
                        ex.getMessage()
                ), ex);

            }
        });
        
    }
}
