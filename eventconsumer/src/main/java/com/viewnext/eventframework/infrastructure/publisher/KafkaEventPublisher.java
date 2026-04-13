/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/publisher/KafkaEventPublisher.java
 */

package com.viewnext.eventframework.infrastructure.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.port.out.EventPublisher;
import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * Implementación del puerto de salida {@link EventPublisher} usando Kafka.
 *
 * <p>Este componente se encarga de:
 * <ul>
 *   <li>Serializar el evento a JSON</li>
 *   <li>Incluir el campo "type" en el mensaje</li>
 *   <li>Enviar el mensaje al topic correspondiente</li>
 *   <li>Registrar logs de publicación</li>
 *   <li>Delegar reintentos y DLQ al listener (no aquí)</li>
 * </ul>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S:</strong> Solo publica eventos en Kafka.</li>
 *   <li><strong>O:</strong> Puede extenderse para añadir headers, particiones, etc.</li>
 *   <li><strong>L:</strong> Sustituible por un mock en tests.</li>
 *   <li><strong>I:</strong> Implementa solo el puerto necesario.</li>
 *   <li><strong>D:</strong> Depende de abstracciones (EventPublisher), no de lógica de negocio.</li>
 * </ul>
 */
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final EventLogger logger;

    public KafkaEventPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            EventLogger logger
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

/*
    @Override
    public void publish(String topic, DomainEvent event) {

        try {
            // -----------------------------------------------------------------
            // 1. Serializar evento a JSON incluyendo el campo "type"
            // -----------------------------------------------------------------
            String json = serializeWithType(event);

            // -----------------------------------------------------------------
            // 2. Enviar mensaje a Kafka
            // -----------------------------------------------------------------
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, json);
            kafkaProducer.send(record);

        } catch (Exception ex) {
            // -----------------------------------------------------------------
            // 3. Log de error
            // -----------------------------------------------------------------
            logger.logPublishError(topic, event, ex);
            throw new RuntimeException("Error publicando evento en Kafka", ex);
        }
    }
*/

    @Override
    public void publish(String topic, DomainEvent event) {
        try {
            ObjectNode tree = objectMapper.valueToTree(event);
            tree.put("type", event.getClass().getSimpleName());
            String json = objectMapper.writeValueAsString(tree);

            kafkaTemplate.send(topic, json);

        } catch (Exception ex) {
            logger.logPublishError(topic, event, ex);
            throw new RuntimeException("Error publicando evento", ex);
        }
    }

    /**
     * Serializa el evento a JSON incluyendo el campo "type".
     */
    private String serializeWithType(DomainEvent event) throws JsonProcessingException {
        ObjectNode tree = objectMapper.valueToTree(event);
        tree.put("type", event.getClass().getSimpleName());
        return objectMapper.writeValueAsString(tree);
    }
}
