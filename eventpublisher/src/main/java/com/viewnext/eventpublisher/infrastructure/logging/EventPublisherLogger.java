package com.viewnext.eventpublisher.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger dedicado del componente Event Publisher.
 * Envuelve un logger SLF4J para centralizar el formato y el nombre.
 */
public class EventPublisherLogger {

    private final Logger logger;

    /**
     * Crea un logger dedicado con el nombre indicado.
     *
     * @param loggerName nombre del logger
     */
    public EventPublisherLogger(String loggerName) {
        this.logger = LoggerFactory.getLogger(loggerName);
    }

    /**
     * Registra un mensaje de nivel INFO.
     *
     * @param message plantilla de mensaje
     * @param args    argumentos del mensaje
     */
    public void info(String message, Object... args) {
        logger.info (message, args);
        logger.info(String.format(message, args));
    }

    /**
     * Registra un mensaje de nivel WARN.
     *
     * @param message plantilla de mensaje
     * @param args    argumentos del mensaje
     */
    public void warn(String message, Object... args) {
        logger.warn (message, args);
        logger.warn(String.format(message, args));
    }

    /**
     * Registra un mensaje de nivel ERROR.
     *
     * @param message plantilla de mensaje
     * @param args    argumentos del mensaje
     */
    public void error(String message, Object... args) {
        logger.error (message, args);
        logger.error(String.format(message, args));
    }

    /**
     * Registra un mensaje de nivel ERROR con excepción.
     *
     * @param message plantilla de mensaje
     * @param throwable excepción asociada
     * @param args    argumentos del mensaje
     */
    public void error(String message, Throwable throwable, Object... args) {
        logger.error (message, args, throwable  );
        logger.error(String.format(message, args), throwable);
    }
}
