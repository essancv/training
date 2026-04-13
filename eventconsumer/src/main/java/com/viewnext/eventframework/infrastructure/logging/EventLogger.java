/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/logging/EventLogger.java
 */

package com.viewnext.eventframework.infrastructure.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.domain.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Componente centralizado de logging para la infraestructura de eventos.
 *
 * <p>Este logger registra trazas detalladas de:
 * <ul>
 *   <li>Publicación de eventos</li>
 *   <li>Consumo de eventos</li>
 *   <li>Errores en publicación o consumo</li>
 * </ul>
 *
 * <p>El comportamiento del logger es configurable mediante
 * {@link LoggingConfigProperties}, permitiendo:
 * <ul>
 *   <li>Enmascarar payloads sensibles</li>
 *   <li>Activar/desactivar logs detallados</li>
 *   <li>Controlar el formato del mensaje</li>
 * </ul>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       Este componente solo gestiona logging.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Nuevos tipos de logs pueden añadirse sin modificar el resto del sistema.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Puede sustituirse por un mock en tests sin romper el sistema.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       No obliga a implementar interfaces adicionales.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       Depende de abstracciones (ObjectMapper, propiedades), no de Kafka.</li>
 * </ul>
 */
public class EventLogger {

    private static final Logger log = LoggerFactory.getLogger(EventLogger.class);

    private final LoggingConfigProperties config;
    private final PayloadMasker payloadMasker;
    private final ObjectMapper objectMapper;

    public EventLogger(
            LoggingConfigProperties config,
            PayloadMasker payloadMasker,
            ObjectMapper objectMapper
    ) {
        this.config = config;
        this.payloadMasker = payloadMasker;
        this.objectMapper = objectMapper;
    }

    // -------------------------------------------------------------------------
    //  MÉTODOS DE LOGGING PARA PUBLICACIÓN
    // -------------------------------------------------------------------------

    /**
     * Log del inicio de la publicación de un evento.
     */
    public void logPublishStart(String topic, DomainEvent event) {
        if (!config.isEnabled()) return;

        log.info("[EVENT-PUBLISH-START] topic={}, type={}, payload={}",
                topic,
                event.getClass().getSimpleName(),
                serialize(event)
        );
    }

    /**
     * Log de publicación correcta.
     */
    public void logPublishSuccess(String topic, DomainEvent event) {
        if (!config.isEnabled()) return;

        log.info("[EVENT-PUBLISH-SUCCESS] topic={}, type={}",
                topic,
                event.getClass().getSimpleName()
        );
    }

    /**
     * Log de error durante la publicación.
     */
    public void logPublishError(String topic, DomainEvent event, Exception ex) {
        if (!config.isEnabled()) return;

        log.error("[EVENT-PUBLISH-ERROR] topic={}, type={}, payload={}, error={}",
                topic,
                event.getClass().getSimpleName(),
                serialize(event),
                ex.getMessage(),
                ex
        );
    }

    // -------------------------------------------------------------------------
    //  MÉTODOS DE LOGGING PARA CONSUMO
    // -------------------------------------------------------------------------

    /**
     * Log del inicio del consumo de un evento.
     */
    public void logConsumeStart(String topic, DomainEvent event) {
        if (!config.isEnabled()) return;

        log.info("[EVENT-CONSUME-START] topic={}, type={}, payload={}",
                topic,
                event.getClass().getSimpleName(),
                serialize(event)
        );
    }

    /**
     * Log de consumo correcto.
     */
    public void logConsumeSuccess(String topic, DomainEvent event) {
        if (!config.isEnabled()) return;

        log.info("[EVENT-CONSUME-SUCCESS] topic={}, type={}",
                topic,
                event.getClass().getSimpleName()
        );
    }

    /**
     * Log de error durante el consumo.
     */
    public void logConsumeError(String topic, DomainEvent event, Exception ex) {
        if (!config.isEnabled()) return;

        log.error("[EVENT-CONSUME-ERROR] topic={}, type={}, payload={}, error={}",
                topic,
                event.getClass().getSimpleName(),
                serialize(event),
                ex.getMessage(),
                ex
        );
    }

    // -------------------------------------------------------------------------
    //  MÉTODO AUXILIAR: SERIALIZACIÓN + ENMASCARAMIENTO
    // -------------------------------------------------------------------------

    private String serialize(DomainEvent event) {
        if (config.isMaskPayload()) {
            return payloadMasker.mask(event);
        }

        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            return "\"<error-serializing-payload>\"";
        }
    }
}