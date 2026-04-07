package com.viewnext.eventpublisher.domain.model;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Representa un mensaje que no ha podido ser entregado
 * al topic original y se envía al dead-letter topic.
 */
public class DeadLetterMessage {

    private final String originalTopic;
    private final String payloadJson;
    private final int attempts;
    private final Instant failedAt;
    private final String errorMessage;
    private final String errorType;

    /**
     * Crea un mensaje de dead-letter.
     *
     * @param originalTopic topic original al que se intentó enviar
     * @param payloadJson   contenido original del mensaje
     * @param attempts      número de intentos realizados
     * @param failedAt      instante del fallo definitivo
     * @param errorMessage  mensaje de error
     * @param errorType     tipo de excepción
     */

        @JsonCreator
        public DeadLetterMessage(
                @JsonProperty("originalTopic") String originalTopic,
                @JsonProperty("payloadJson") String payloadJson,
                @JsonProperty("attempts") int attempts,
                @JsonProperty("failedAt") Instant failedAt,
                @JsonProperty("errorMessage") String errorMessage,
                @JsonProperty("errorType") String errorType
        ) {
            this.originalTopic = originalTopic;
        this.payloadJson = payloadJson;
        this.attempts = attempts;
        this.failedAt = failedAt;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public String getOriginalTopic() {
        return originalTopic;
    }

    public String getPayloadJson() {
        return payloadJson;
    }

    public int getAttempts() {
        return attempts;
    }

    public Instant getFailedAt() {
        return failedAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }
}
