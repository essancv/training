package com.viewnext.eventpublisher.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Propiedades de configuración del componente Event Publisher.
 */
@ConfigurationProperties(prefix = "event.publisher")
public class EventPublisherProperties {

    /**
     * Servidores de Kafka (bootstrap servers).
     * (Se usa normalmente por la configuración global de Kafka del proyecto).
     */
    private String bootstrapServers;

    /**
     * Número máximo de reintentos.
     */
    private int retryMaxAttempts = 3;

    /**
     * Tiempo de espera entre reintentos (ms).
     */
    private long retryBackoffMs = 2000L;

    /**
     * Nombre del dead-letter topic.
     */
    private String deadLetterTopic = "event-publisher-dead-letter";

    /**
     * Nombre del logger dedicado.
     */
    private String loggerName = "event-publisher";

    /**
     * Nombre del fichero de log (se configura en el logging del proyecto).
     */
    private String loggerFile = "event-publisher.log";

    /**
     * Nivel de log (INFO, DEBUG, etc.).
     */
    private String loggerLevel = "INFO";

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public int getRetryMaxAttempts() {
        return retryMaxAttempts;
    }

    public void setRetryMaxAttempts(int retryMaxAttempts) {
        this.retryMaxAttempts = retryMaxAttempts;
    }

    public long getRetryBackoffMs() {
        return retryBackoffMs;
    }

    public void setRetryBackoffMs(long retryBackoffMs) {
        this.retryBackoffMs = retryBackoffMs;
    }

    public String getDeadLetterTopic() {
        return deadLetterTopic;
    }

    public void setDeadLetterTopic(String deadLetterTopic) {
        this.deadLetterTopic = deadLetterTopic;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerFile() {
        return loggerFile;
    }

    public void setLoggerFile(String loggerFile) {
        this.loggerFile = loggerFile;
    }

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(String loggerLevel) {
        this.loggerLevel = loggerLevel;
    }
}
