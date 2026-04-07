package com.viewnext.eventpublisher.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventpublisher.domain.model.DeadLetterMessage;
import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.Instant;

import static org.mockito.Mockito.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class KafkaDeadLetterAdapterTest {

        @Test
        void testSendToDeadLetter() throws Exception {
        KafkaTemplate<String, String> template = mock(KafkaTemplate.class);
        EventPublisherPort publisher = mock(EventPublisherPort.class);

       ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
System.out.println("Mapper usado = " + mapper);

        EventPublisherLogger logger = mock(EventPublisherLogger.class);
        MessageListenerContainer container = mock(MessageListenerContainer.class);

        KafkaDeadLetterAdapter adapter = new KafkaDeadLetterAdapter(
                template, publisher, mapper, logger, "dead-topic", container
        );

        DeadLetterMessage msg = new DeadLetterMessage(
                "topic", "{}", 3, Instant.now(), "error", "RuntimeException"
        );
        System.out.println("Adapter class = " + adapter.getClass());
        System.out.println("KafkaTemplate mock = " + template);

        adapter.sendToDeadLetter(msg);

        verify(template, times(1)).send(eq("dead-topic"), anyString());
        }


        @Test
        void testHandleDeadLetterRecord() throws Exception {
        KafkaTemplate<String, String> template = mock(KafkaTemplate.class);
        EventPublisherPort publisher = mock(EventPublisherPort.class);

        // 🔥 FIX: soporta Instant
        ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
System.out.println("Mapper usado = " + mapper);
       EventPublisherLogger logger = mock(EventPublisherLogger.class);
        MessageListenerContainer container = mock(MessageListenerContainer.class);

        KafkaDeadLetterAdapter adapter = new KafkaDeadLetterAdapter(
                template, publisher, mapper, logger, "dead-topic", container
        );

        DeadLetterMessage msg = new DeadLetterMessage(
                "topic", "{}", 1, Instant.now(), "error", "RuntimeException"
        );

        String json = mapper.writeValueAsString(msg);

        ConsumerRecord<String, String> record =
                new ConsumerRecord<>("dead-topic", 0, 0, null, json);

        adapter.handleDeadLetterRecord(record);

        verify(publisher, times(1)).publish(any());
        }
}
