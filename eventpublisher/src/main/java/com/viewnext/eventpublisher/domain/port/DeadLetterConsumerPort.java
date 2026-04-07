package com.viewnext.eventpublisher.domain.port;

/**
 * Puerto de salida para consumir mensajes del dead-letter topic.
 */
public interface DeadLetterConsumerPort {

    /**
     * Consume mensajes del dead-letter topic y los procesa.
     */
    void consumeDeadLetters();
}
