package com.viewnext.eventpublisher.infrastructure.kafka;

import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;
import java.time.Instant;

import static org.mockito.Mockito.*;

class KafkaEventPublisherAdapterTest {

    @Test
    void testPublish() {
        KafkaTemplate<String, String> kafkaTemplate = mock(KafkaTemplate.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        KafkaEventPublisherAdapter adapter =
                new KafkaEventPublisherAdapter(kafkaTemplate, logger);

        OutboundEvent event = new OutboundEvent("qa-topic", "payload", 1, Instant.now());

        // 👉 Mock correcto del CompletableFuture
        CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
        future.complete(null); // éxito simulado

        when(kafkaTemplate.send("qa-topic", "payload")).thenReturn(future);

        adapter.publish(event);

        verify(kafkaTemplate, times(1)).send("qa-topic", "payload");
    }
}
