package com.viewnext.eventpublisher.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventpublisher.application.service.PublishEventUseCase;
import com.viewnext.eventpublisher.domain.model.OutboundEvent;
import com.viewnext.eventpublisher.domain.service.EventPublishingService;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PublishEventUseCaseTest {
    record Dummy(String id) {}


    @Test
    void testPublishAsync_SubmitsRunnable() throws Exception {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        EventPublishingService service = mock(EventPublishingService.class);
        ExecutorService executor = mock(ExecutorService.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventUseCase useCase = new PublishEventUseCase(mapper, service, executor, logger);

        useCase.publishAsync("topic", new Dummy("123"));

        verify(executor, times(1)).submit(any(Runnable.class));
    }

    @Test
    void testPublishAsync_RunnableCallsService() throws Exception {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        EventPublishingService service = mock(EventPublishingService.class);

        // Simulamos ejecución inmediata del runnable
        ExecutorService executor = mock(ExecutorService.class);
        when(executor.submit(any(Runnable.class))).thenAnswer(invocation -> {
            Runnable r = invocation.getArgument(0);
            r.run(); // ejecutamos el runnable directamente
            return mock(Future.class);
        });

        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventUseCase useCase = new PublishEventUseCase(mapper, service, executor, logger);

        Dummy payload = new Dummy("ABC");
        useCase.publishAsync("topic", payload);

        verify(service, times(1)).publishWithRetry(any(OutboundEvent.class));
    }

}