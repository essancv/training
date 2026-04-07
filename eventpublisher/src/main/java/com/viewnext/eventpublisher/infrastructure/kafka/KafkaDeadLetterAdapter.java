package com.viewnext.eventpublisher.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventpublisher.domain.model.DeadLetterMessage;
import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.port.DeadLetterConsumerPort;
import com.viewnext.eventpublisher.domain.port.DeadLetterPort;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.io.IOException;

/**
 * Adaptador para gestionar el dead-letter topic:
 * - Enviar mensajes al dead-letter topic.
 * - Consumir mensajes del dead-letter topic y reintentar su envío.
 */
public class KafkaDeadLetterAdapter implements DeadLetterPort, DeadLetterConsumerPort,
    MessageListener<String, String> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventPublisherPort eventPublisherPort;
    private final ObjectMapper objectMapper;
    private final EventPublisherLogger logger;
    private final String deadLetterTopic;
    private final MessageListenerContainer deadLetterListenerContainer;

    /**
     * Crea el adaptador de dead-letter.
     *
     * @param kafkaTemplate              plantilla de Kafka
     * @param eventPublisherPort         puerto de publicación de eventos
     * @param objectMapper               serializador JSON
     * @param logger                     logger dedicado
     * @param deadLetterTopic            nombre del dead-letter topic
     * @param deadLetterListenerContainer contenedor de escucha para dead-letter
     */
    public KafkaDeadLetterAdapter(KafkaTemplate<String, String> kafkaTemplate,
                                  EventPublisherPort eventPublisherPort,
                                  ObjectMapper objectMapper,
                                  EventPublisherLogger logger,
                                  String deadLetterTopic,
                                  MessageListenerContainer deadLetterListenerContainer) {
        this.kafkaTemplate = kafkaTemplate;
        this.eventPublisherPort = eventPublisherPort;
        this.objectMapper = objectMapper;
        this.logger = logger;
        this.deadLetterTopic = deadLetterTopic;
        this.deadLetterListenerContainer = deadLetterListenerContainer;
    }

    @Override
    public void sendToDeadLetter(DeadLetterMessage message) {
        System.out.println("Ejecutando sendToDeadLetter");
        try {
            String json = objectMapper.writeValueAsString(message);
            logger.warn(String.format("Enviando mensaje a dead-letter topic='%s'", deadLetterTopic));
            kafkaTemplate.send(deadLetterTopic, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("Error serializando mensaje para dead-letter topic='%s': %s",
                    deadLetterTopic, e.getMessage(), e))    ;
        }
    }

    @Override
    public void consumeDeadLetters() {
        logger.info("Iniciando consumo de mensajes desde dead-letter topic='%s'", deadLetterTopic);
        // Se asume que el contenedor está configurado para este topic.
        // Aquí simplemente se asegura que el contenedor esté activo.
        deadLetterListenerContainer.start();
    }


    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        handleDeadLetterRecord(record);
    }
    
    /**
     * Procesa un mensaje individual del dead-letter topic.
     * Este método puede ser usado por el listener de Kafka.
     *
     * @param record registro consumido de Kafka
     */
    public void handleDeadLetterRecord(ConsumerRecord<String, String> record) {
        System.out.println("Publicando evento...");
        try {
            DeadLetterMessage message = objectMapper.readValue(record.value(), DeadLetterMessage.class);
            logger.info(String.format("Reprocesando mensaje desde dead-letter. Topic original='%s'", message.getOriginalTopic()));
            OutboundEvent event = new OutboundEvent(
                    message.getOriginalTopic(),
                    message.getPayloadJson(),
                    message.getAttempts() + 1,
                    message.getFailedAt()
            );
            eventPublisherPort.publish(event);
            logger.info(String.format("Mensaje reprocesado correctamente para topic='%s'", message.getOriginalTopic()));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(String.format("Error deserializando mensaje de dead-letter: %s", e.getMessage()), e);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(String.format("Error reprocesando mensaje de dead-letter: %s", ex.getMessage()), ex);
        }
    }
}
