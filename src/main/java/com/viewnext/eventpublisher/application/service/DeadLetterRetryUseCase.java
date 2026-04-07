package com.viewnext.eventpublisher.application.service;

import com.viewnext.eventpublisher.domain.service.DeadLetterRetryService;

/**
 * Caso de uso de aplicación para reprocesar mensajes
 * desde el dead-letter topic.
 */
public class DeadLetterRetryUseCase {

    private final DeadLetterRetryService deadLetterRetryService;

    /**
     * Crea el caso de uso de reproceso de dead-letter.
     *
     * @param deadLetterRetryService servicio de dominio de reproceso
     */
    public DeadLetterRetryUseCase(DeadLetterRetryService deadLetterRetryService) {
        this.deadLetterRetryService = deadLetterRetryService;
    }

    /**
     * Ejecuta el reproceso de mensajes desde el dead-letter topic.
     */
    public void processDeadLetters() {
        deadLetterRetryService.processDeadLetters();
    }
}
