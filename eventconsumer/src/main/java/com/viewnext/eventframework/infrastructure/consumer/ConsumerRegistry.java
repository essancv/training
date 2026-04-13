/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/consumer/ConsumerRegistry.java
 */

package com.viewnext.eventframework.infrastructure.consumer;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Registro centralizado de consumidores detectados por {@link ConsumeEventScanner}.
 *
 * <p>Permite que la infraestructura conozca qué métodos deben ejecutarse
 * cuando llega un evento de un topic concreto.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Mantener un mapa de:
 * <pre>
 * topic → lista de consumidores (bean + método + tipo de evento)
 * </pre>
 * </p>
 *
 * <h2>Tareas internas</h2>
 * <ul>
 *   <li>Registrar consumidores detectados</li>
 *   <li>Permitir recuperar consumidores por topic</li>
 *   <li>Validar duplicados o inconsistencias</li>
 * </ul>
 */
public class ConsumerRegistry {

    /**
     * Estructura interna:
     * topic → lista de consumidores registrados
     */
    private final Map<String, List<RegisteredConsumer>> consumersByTopic = new HashMap<>();

    /**
     * Registra un consumidor detectado por el escáner.
     */
    public void registerConsumer(String topic, Class<?> eventType, Object bean, Method method) {

        consumersByTopic
            .computeIfAbsent(topic, t -> new ArrayList<>())
            .add(new RegisteredConsumer(eventType, bean, method));
    }

    /**
     * Devuelve la lista de consumidores registrados para un topic.
     */
    public List<RegisteredConsumer> getConsumers(String topic) {
        return consumersByTopic.getOrDefault(topic, Collections.emptyList());
    }

    /**
     * Representa un consumidor registrado.
     */
    public record RegisteredConsumer(
        Class<?> eventType,
        Object bean,
        Method method
    ) {}

    public List<String> getAllTopics() {
    return new ArrayList<>(consumersByTopic.keySet());
}
}
