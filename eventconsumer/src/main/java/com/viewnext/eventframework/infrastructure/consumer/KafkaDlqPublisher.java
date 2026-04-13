package com.viewnext.eventframework.infrastructure.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Implementación de la DLQ usando KafkaTemplate.
 *
 * Envía el mensaje original a un topic de DLQ, preservando:
 * - key
 * - payload
 *
 * Se pueden añadir headers con metadatos si se desea.
 */
public class KafkaDlqPublisher implements EventListenerContainer.DlqPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String dlqTopic;

    public KafkaDlqPublisher(KafkaTemplate<String, String> kafkaTemplate, String dlqTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.dlqTopic = dlqTopic;
    }

    @Override
    public void publishToDlq(ConsumerRecord<String, String> record) {
        kafkaTemplate.send(dlqTopic, record.key(), record.value());
    }
}