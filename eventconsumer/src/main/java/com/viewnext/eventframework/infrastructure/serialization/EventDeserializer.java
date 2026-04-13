/**
 * Estructura del directorio:
 * src/main/java/com/viewnext/eventframework/infrastructure/serialization/EventDeserializer.java
 */

package com.viewnext.eventframework.infrastructure.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.domain.event.DomainEvent;

import java.util.Map;

/**
 * Deserializador de eventos basado en el campo "type".
 *
 * <p>Este componente permite convertir un JSON recibido desde Kafka en
 * una instancia del evento correcto.</p>
 *
 * <h2>Funcionamiento</h2>
 * <ul>
 *   <li>Lee el campo "type" del JSON</li>
 *   <li>Busca la clase correspondiente en el mapa de tipos</li>
 *   <li>Deserializa el JSON a esa clase</li>
 * </ul>
 */
public class EventDeserializer {

    private final ObjectMapper objectMapper;

    /**
     * Mapa: nombre del tipo → clase del evento.
     */
    private final Map<String, Class<? extends DomainEvent>> eventTypes;

    public EventDeserializer(
            ObjectMapper objectMapper,
            Map<String, Class<? extends DomainEvent>> eventTypes
    ) {
        this.objectMapper = objectMapper;
        this.eventTypes = eventTypes;
    }

    /**
     * Deserializa un JSON a un evento concreto.
     */
    public DomainEvent deserialize(String json) {
        try {
            JsonNode tree = objectMapper.readTree(json);

            String type = tree.get("type").asText();
            Class<? extends DomainEvent> clazz = eventTypes.get(type);

            if (clazz == null) {
                throw new IllegalStateException("Tipo de evento desconocido: " + type);
            }

            return objectMapper.treeToValue(tree, clazz);

        } catch (Exception e) {
            throw new RuntimeException("Error deserializando evento", e);
        }
    }
}
