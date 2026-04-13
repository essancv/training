package com.viewnext.eventpublisher.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.service.EventPublishingService;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;

import java.time.Instant;
import java.util.concurrent.ExecutorService;

/**
 * Caso de uso de aplicación para publicar un evento a partir
 * de un objeto de dominio y un topic.
 */
public class PublishEventUseCase {

    private final ObjectMapper objectMapper;
    private final EventPublishingService eventPublishingService;
    private final ExecutorService executorService;
    private final EventPublisherLogger logger;

    /**
     * Crea el caso de uso de publicación de eventos.
     *
     * @param objectMapper           serializador JSON
     * @param eventPublishingService servicio de dominio de publicación
     * @param executorService        ejecutor para tareas asíncronas
     * @param logger                 logger dedicado del componente
     */
    public PublishEventUseCase(ObjectMapper objectMapper,
                               EventPublishingService eventPublishingService,
                               ExecutorService executorService,
                               EventPublisherLogger logger) {
        this.objectMapper =  objectMapper.findAndRegisterModules();
        this.eventPublishingService = eventPublishingService;
        this.executorService = executorService;
        this.logger = logger;
    }

    /**
     * Publica un evento de forma asíncrona.
     *
     * @param topic nombre del topic de Kafka
     * @param value objeto a serializar y enviar
     */
    public void publishAsync(String topic, Object value) {
        System.out.println(" *************** Iniciando publicación asíncrona en topic '" + topic + "' con valor: " + value);
        try {
            String json = objectMapper.writeValueAsString(value);
            OutboundEvent event = new OutboundEvent(topic, json, 1, Instant.now());
            logger.info("Programando publicación asíncrona en topic {%s}", topic);
            executorService.submit(() -> eventPublishingService.publishWithRetry(event));
        } catch (JsonProcessingException e) {
            logger.error("Error serializando evento para topic '{%s}': {%s}", topic, e.getMessage(), e);
        }
    }
}
