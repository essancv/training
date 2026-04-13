/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/serialization/EventTypeRegistry.java
 */

package com.viewnext.eventframework.infrastructure.serialization;

import com.viewnext.eventframework.domain.event.DomainEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro centralizado de tipos de eventos.
 *
 * Permite:
 * - Registrar clases de eventos
 * - Resolver el tipo a partir del nombre (simpleName)
 */
public class EventTypeRegistry {

    private final Map<String, Class<? extends DomainEvent>> types = new HashMap<>();

    /**
     * Registra un tipo de evento.
     */
    public void register(Class<? extends DomainEvent> eventClass) {
        types.put(eventClass.getSimpleName(), eventClass);
    }

    /**
     * Devuelve el mapa de tipos para el deserializador.
     */
    public Map<String, Class<? extends DomainEvent>> getTypes() {
        return Map.copyOf(types);
    }
}
