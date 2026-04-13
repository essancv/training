/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/serialization/EventSerializer.java
 */

package com.viewnext.eventframework.infrastructure.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * Serializador de eventos a JSON, añadiendo el campo "type" para permitir
 * deserialización polimórfica.
 *
 * <p>Este componente es usado por el publicador Kafka y por el sistema
 * de logging cuando el payload no está enmascarado.</p>
 */
public class EventSerializer {

    private final ObjectMapper objectMapper;

    public EventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Serializa un evento a JSON incluyendo el campo "type".
     */
    public String serialize(DomainEvent event) {
        try {
            ObjectNode tree = objectMapper.valueToTree(event);
            tree.put("type", event.getClass().getSimpleName());
            return objectMapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando evento", e);
        }
    }
}
