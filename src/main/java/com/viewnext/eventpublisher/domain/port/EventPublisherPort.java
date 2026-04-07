package com.viewnext.eventpublisher.domain.port;

import com.viewnext.eventpublisher.domain.model.OutboundEvent;

/**
 * Puerto de salida para publicar eventos en un sistema de mensajería (Kafka).
 */
public interface EventPublisherPort {

    /**
     * Publica un evento en el sistema de mensajería.
     *
     * @param event evento a publicar
     */
    void publish(OutboundEvent event);
}
