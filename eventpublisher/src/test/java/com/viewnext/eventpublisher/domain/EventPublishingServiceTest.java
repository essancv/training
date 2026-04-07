package com.viewnext.eventpublisher.domain;

import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.model.RetryPolicy;
import com.viewnext.eventpublisher.domain.port.DeadLetterPort;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.domain.service.EventPublishingService;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.*;

class EventPublishingServiceTest {

    private EventPublisherPort publisher;
    private DeadLetterPort deadLetterPort;
    private EventPublisherLogger logger;
    private RetryPolicy retryPolicy;

    @BeforeEach
    void setup() {
        publisher = mock(EventPublisherPort.class);
        deadLetterPort = mock(DeadLetterPort.class);
        logger = mock(EventPublisherLogger.class);
        retryPolicy = new RetryPolicy(2, Duration.ofMillis(1));
    }

    @Test
    void testPublishSuccess() {
        EventPublishingService service = new EventPublishingService(publisher, deadLetterPort, retryPolicy, logger);

        OutboundEvent event = new OutboundEvent("topic", "{}", 1, Instant.now());

        service.publishWithRetry(event);

        verify(publisher, times(1)).publish(event);
        verify(deadLetterPort, never()).sendToDeadLetter(any());
    }

    @Test
    void testPublishWithRetriesAndFailure() {
        EventPublishingService service = new EventPublishingService(publisher, deadLetterPort, retryPolicy, logger);

        OutboundEvent event = new OutboundEvent("topic", "{}", 1, Instant.now());

        doThrow(new RuntimeException("fail")).when(publisher).publish(any());

        service.publishWithRetry(event);

        verify(publisher, times(2)).publish(any());
        verify(deadLetterPort, times(1)).sendToDeadLetter(any());
    }
}
