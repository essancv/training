package com.viewnext.eventpublisher.domain.service;

import com.viewnext.eventpublisher.domain.model.DeadLetterMessage;
import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.model.RetryPolicy;
import com.viewnext.eventpublisher.domain.port.DeadLetterPort;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;

import java.time.Instant;

/**
 * Servicio de dominio responsable de aplicar la política de reintentos
 * y decidir cuándo enviar un mensaje al dead-letter topic.
 */
public class EventPublishingService {

    private final EventPublisherPort eventPublisherPort;
    private final DeadLetterPort deadLetterPort;
    private final RetryPolicy retryPolicy;
    private final EventPublisherLogger logger;

    /**
     * Crea el servicio de publicación de eventos.
     *
     * @param eventPublisherPort puerto para publicar eventos
     * @param deadLetterPort     puerto para enviar mensajes a dead-letter
     * @param retryPolicy        política de reintentos
     * @param logger             logger dedicado del componente
     */
    public EventPublishingService(EventPublisherPort eventPublisherPort,
                                  DeadLetterPort deadLetterPort,
                                  RetryPolicy retryPolicy,
                                  EventPublisherLogger logger) {
        this.eventPublisherPort = eventPublisherPort;
        this.deadLetterPort = deadLetterPort;
        this.retryPolicy = retryPolicy;
        this.logger = logger;
    }

    /**
     * Publica un evento aplicando la política de reintentos.
     * Este método está pensado para ser invocado de forma asíncrona.
     *
     * @param event evento a publicar
     */
    public void publishWithRetry(OutboundEvent event) {
        OutboundEvent current = event;
        while (true) {
            try {
                logger.info("Publicando evento en topic {}, intento {}", current.getTopic(), current.getAttempt());
                eventPublisherPort.publish(current);
                logger.info("Evento publicado correctamente en topic '{}'", current.getTopic());
                return;
            } catch (Exception ex) {
                logger.error("Error al publicar evento en topic '{}' (intento {}): {}",
                        current.getTopic(), current.getAttempt(), ex.getMessage(), ex);

                if (retryPolicy.canRetry(current.getAttempt())) {
                    try {
                        Thread.sleep(retryPolicy.getBackoff().toMillis());
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.error("Hilo de reintentos interrumpido para topic '{}'", current.getTopic(), ie);
                        sendToDeadLetter(current, ex);
                        return;
                    }
                    current = current.nextAttempt();
                } else {
                    logger.warn("Agotados reintentos para topic '{}'. Enviando a dead-letter.", current.getTopic());
                    sendToDeadLetter(current, ex);
                    return;
                }
            }
        }
    }

    /**
     * Envía un evento al dead-letter topic construyendo el mensaje correspondiente.
     *
     * @param event evento que no se ha podido entregar
     * @param ex    excepción que causó el fallo
     */
    private void sendToDeadLetter(OutboundEvent event, Exception ex) {
        DeadLetterMessage message = new DeadLetterMessage(
                event.getTopic(),
                event.getPayloadJson(),
                event.getAttempt(),
                Instant.now(),
                ex.getMessage(),
                ex.getClass().getName()
        );
        deadLetterPort.sendToDeadLetter(message);
    }
}
