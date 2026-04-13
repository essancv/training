/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/consumer/KafkaEventListener.java
 */

package com.viewnext.eventframework.infrastructure.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.service.ProcessConsumedEventUseCase;
import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.lang.reflect.Method;

/**
 * Listener encargado de recibir mensajes desde Kafka, deserializarlos,
 * registrar logs y delegar en el caso de uso {@link ProcessConsumedEventUseCase}.
 *
 * <p>Este componente NO ejecuta directamente los métodos consumidores.
 * En su lugar:
 * <ul>
 *   <li>Obtiene los consumidores registrados en {@link ConsumerRegistry}</li>
 *   <li>Deserializa el JSON a la clase indicada en la anotación</li>
 *   <li>Registra logs de inicio y fin</li>
 *   <li>Invoca el caso de uso de procesamiento</li>
 *   <li>Gestiona errores, reintentos y DLQ</li>
 * </ul>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Ser el punto de entrada de Kafka hacia la arquitectura limpia.
 * </p>
 */
public class KafkaEventListener {

    private final ConsumerRegistry registry;
    private final ObjectMapper objectMapper;
    private final ProcessConsumedEventUseCase processUseCase;
    private final EventLogger logger;

    public KafkaEventListener(
            ConsumerRegistry registry,
            ObjectMapper objectMapper,
            ProcessConsumedEventUseCase processUseCase,
            EventLogger logger
    ) {
        this.registry = registry;
        this.objectMapper = objectMapper;
        this.processUseCase = processUseCase;
        this.logger = logger;
    }

    /**
     * Método invocado por el contenedor Kafka (KafkaListenerContainer).
     */
    public void onMessage(ConsumerRecord<String, String> record) {

        String topic = record.topic();
        String payload = record.value();

        try {
            // -----------------------------------------------------------------
            // 1. Obtener consumidores registrados para el topic
            // -----------------------------------------------------------------
            var consumers = registry.getConsumers(topic);

            if (consumers.isEmpty()) {
                throw new IllegalStateException("No hay consumidores registrados para el topic " + topic);
            }

            // -----------------------------------------------------------------
            // 2. Parsear JSON
            // -----------------------------------------------------------------
            JsonNode json = objectMapper.readTree(payload);

            // -----------------------------------------------------------------
            // 3. Obtener tipo del evento desde el JSON
            // -----------------------------------------------------------------
            String type = json.get("type").asText();

            for (var consumer : consumers) {

                if (!consumer.eventType().getSimpleName().equals(type)) {
                    continue; // No coincide el tipo
                }

                // -------------------------------------------------------------
                // 4. Deserializar evento al tipo correcto
                // -------------------------------------------------------------
                DomainEvent event = (DomainEvent) objectMapper.treeToValue(json, consumer.eventType());

                // -------------------------------------------------------------
                // 5. Logging de inicio
                // -------------------------------------------------------------
                logger.logConsumeStart(topic, event);

                try {
                    // ---------------------------------------------------------
                    // 6. Delegar en el caso de uso
                    // ---------------------------------------------------------
                    Method method = consumer.method();
                    Object bean = consumer.bean();

                    // Invocamos el caso de uso, que a su vez invoca el handler
                    processUseCase.process(event, e -> {
                        try {
                            method.invoke(bean, e);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    // ---------------------------------------------------------
                    // 7. Logging de éxito
                    // ---------------------------------------------------------
                    logger.logConsumeSuccess(topic, event);

                } catch (Exception ex) {
                    // ---------------------------------------------------------
                    // 8. Logging de error
                    // ---------------------------------------------------------
                    logger.logConsumeError(topic, event, ex);

                    // Aquí se integrará:
                    // - Reintentos
                    // - DLQ
                    throw ex;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("Error procesando mensaje Kafka", ex);
        }
    }
}
