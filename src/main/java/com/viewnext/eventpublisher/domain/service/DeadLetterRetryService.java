package com.viewnext.eventpublisher.domain.service;

import com.viewnext.eventpublisher.domain.port.DeadLetterConsumerPort;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;

/**
 * Servicio de dominio responsable de orquestar el reproceso
 * de mensajes desde el dead-letter topic.
 */
public class DeadLetterRetryService {

    private final DeadLetterConsumerPort deadLetterConsumerPort;
    private final EventPublisherLogger logger;

    /**
     * Crea el servicio de reproceso de dead-letter.
     *
     * @param deadLetterConsumerPort puerto para consumir mensajes de dead-letter
     * @param logger                 logger dedicado del componente
     */
    public DeadLetterRetryService(DeadLetterConsumerPort deadLetterConsumerPort,
                                  EventPublisherLogger logger) {
        this.deadLetterConsumerPort = deadLetterConsumerPort;
        this.logger = logger;
    }

    /**
     * Ejecuta el proceso de reintentos desde el dead-letter topic.
     */
    public void processDeadLetters() {
        logger.info("Iniciando reproceso de mensajes desde dead-letter topic");
        deadLetterConsumerPort.consumeDeadLetters();
        logger.info("Finalizado reproceso de mensajes desde dead-letter topic");
    }
}
