package com.viewnext.eventpublisher.domain.model;

import java.time.Duration;

/**
 * Define la política de reintentos para el envío de eventos.
 */
public class RetryPolicy {

    private final int maxAttempts;
    private final Duration backoff;

    /**
     * Crea una política de reintentos.
     *
     * @param maxAttempts número máximo de intentos
     * @param backoff     tiempo de espera entre intentos
     */
    public RetryPolicy(int maxAttempts, Duration backoff) {
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public Duration getBackoff() {
        return backoff;
    }

    /**
     * Indica si se puede realizar un nuevo intento.
     *
     * @param attempt intento actual
     * @return true si attempt &lt; maxAttempts
     */
    public boolean canRetry(int attempt) {
        return attempt < maxAttempts;
    }
}
