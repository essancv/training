package com.viewnext.eventpublisher.domain.model;

import java.time.Instant;

/**
 * Representa un evento listo para ser enviado a Kafka.
 */
public class OutboundEvent {

    private final String topic;
    private final String payloadJson;
    private final int attempt;
    private final Instant createdAt;

    /**
     * Crea un nuevo evento de salida.
     *
     * @param topic       topic de Kafka destino
     * @param payloadJson contenido del mensaje en JSON
     * @param attempt     número de intento actual
     * @param createdAt   instante de creación del evento
     */
    public OutboundEvent(String topic, String payloadJson, int attempt, Instant createdAt) {
        this.topic = topic;
        this.payloadJson = payloadJson;
        this.attempt = attempt;
        this.createdAt = createdAt;
    }

    public String getTopic() {
        return topic;
    }

    public String getPayloadJson() {
        return payloadJson;
    }

    public int getAttempt() {
        return attempt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Crea una copia del evento incrementando el número de intento.
     *
     * @return nuevo OutboundEvent con attempt + 1
     */
    public OutboundEvent nextAttempt() {
        return new OutboundEvent(this.topic, this.payloadJson, this.attempt + 1, this.createdAt);
    }
}
