package com.viewnext.eventpublisher.domain.port;

import com.viewnext.eventpublisher.domain.model.DeadLetterMessage;

/**
 * Puerto de salida para enviar mensajes al dead-letter topic.
 */
public interface DeadLetterPort {

    /**
     * Envía un mensaje al dead-letter topic.
     *
     * @param message mensaje de dead-letter
     */
    void sendToDeadLetter(DeadLetterMessage message);
}
