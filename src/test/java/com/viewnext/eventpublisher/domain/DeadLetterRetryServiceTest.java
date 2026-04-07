package com.viewnext.eventpublisher.domain;

import com.viewnext.eventpublisher.domain.port.DeadLetterConsumerPort;
import com.viewnext.eventpublisher.domain.service.DeadLetterRetryService;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeadLetterRetryServiceTest {

    @Test
    void testProcessDeadLetters() {
        DeadLetterConsumerPort consumer = mock(DeadLetterConsumerPort.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        DeadLetterRetryService service = new DeadLetterRetryService(consumer, logger);

        service.processDeadLetters();

        verify(consumer, times(1)).consumeDeadLetters();
    }
}
