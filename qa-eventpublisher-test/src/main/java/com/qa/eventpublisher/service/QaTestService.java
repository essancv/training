package com.qa.eventpublisher.service;

import com.viewnext.eventpublisher.infrastructure.aop.PublishEvent;
import org.springframework.stereotype.Service;

/**
 * Servicio de prueba que expone métodos anotados con @PublishEvent
 * para validar el comportamiento del starter.
 */
@Service
public class QaTestService {

    /**
     * Evento simple que debe publicarse correctamente.
     *
     * @return objeto de evento de prueba
     */
    @PublishEvent(topic = "qa-topic")
    public QaTestEvent okEvent() {
        return new QaTestEvent("OK", "Evento correcto");
    }

    /**
     * Método que devuelve null: no debe publicarse ningún evento.
     *
     * @return null
     */
    @PublishEvent(topic = "qa-topic")
    public QaTestEvent nullEvent() {
        return null;
    }

    /**
     * Método que lanza excepción: no debe publicarse ningún evento.
     */
    @PublishEvent(topic = "qa-topic")
    public QaTestEvent exceptionEvent() {
        throw new RuntimeException("Fallo simulado en método anotado");
    }

    /**
     * Evento para probar volumen o repetición.
     *
     * @param id identificador
     * @return evento de prueba
     */
    @PublishEvent(topic = "qa-topic")
    public QaTestEvent customEvent(String id) {
        return new QaTestEvent(id, "Evento custom");
    }

    /**
     * DTO simple para el evento de prueba.
     */
    public record QaTestEvent(String code, String message) {
    }
}
