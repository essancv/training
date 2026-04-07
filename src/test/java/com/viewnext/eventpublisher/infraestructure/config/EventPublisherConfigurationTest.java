package com.viewnext.eventpublisher.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventpublisher.application.service.DeadLetterRetryUseCase;
import com.viewnext.eventpublisher.application.service.PublishEventUseCase;
import com.viewnext.eventpublisher.domain.model.RetryPolicy;
import com.viewnext.eventpublisher.domain.port.DeadLetterConsumerPort;
import com.viewnext.eventpublisher.domain.port.DeadLetterPort;
import com.viewnext.eventpublisher.domain.port.EventPublisherPort;
import com.viewnext.eventpublisher.domain.service.DeadLetterRetryService;
import com.viewnext.eventpublisher.domain.service.EventPublishingService;
import com.viewnext.eventpublisher.infrastructure.aop.PublishEventAspect;
import com.viewnext.eventpublisher.infrastructure.kafka.KafkaDeadLetterAdapter;
import com.viewnext.eventpublisher.infrastructure.kafka.KafkaEventPublisherAdapter;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventPublisherConfigurationTest {

    private final EventPublisherConfiguration config = new EventPublisherConfiguration();

    // -------------------------------------------------------------
    // LOGGER
    // -------------------------------------------------------------
    @Test
    void testEventPublisherLoggerCreation() {
        EventPublisherProperties props = mock(EventPublisherProperties.class);
        when(props.getLoggerName()).thenReturn("test-logger");

        EventPublisherLogger logger = config.eventPublisherLogger(props);

        assertNotNull(logger);
    }

    // -------------------------------------------------------------
    // RETRY POLICY
    // -------------------------------------------------------------
    @Test
    void testRetryPolicyCreation() {
        EventPublisherProperties props = mock(EventPublisherProperties.class);
        when(props.getRetryMaxAttempts()).thenReturn(5);
        when(props.getRetryBackoffMs()).thenReturn(200L);

        RetryPolicy policy = config.retryPolicy(props);

        assertNotNull(policy);
        assertEquals(5, policy.getMaxAttempts());
        assertEquals(Duration.ofMillis(200), policy.getBackoff());
    }

    // -------------------------------------------------------------
    // EVENT PUBLISHER PORT
    // -------------------------------------------------------------
    @Test
    void testEventPublisherPortCreation() {
        KafkaTemplate<String, String> template = mock(KafkaTemplate.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        EventPublisherPort port = config.eventPublisherPort(template, logger);

        assertNotNull(port);
        assertTrue(port instanceof KafkaEventPublisherAdapter);
    }

    // -------------------------------------------------------------
    // DEAD LETTER LISTENER CONTAINER
    // -------------------------------------------------------------
    @Test
    void testDeadLetterListenerContainerCreation() {
        ConsumerFactory<String, String> factory = mock(ConsumerFactory.class);
        EventPublisherProperties props = mock(EventPublisherProperties.class);
        KafkaDeadLetterAdapter adapter = mock(KafkaDeadLetterAdapter.class);

        when(props.getDeadLetterTopic()).thenReturn("dead-topic");

        ConcurrentMessageListenerContainer<String, String> container =
                config.deadLetterListenerContainer(factory, props);

        assertNotNull(container);
    }

    // -------------------------------------------------------------
    // DEAD LETTER ADAPTER
    // -------------------------------------------------------------
    @Test
    void testKafkaDeadLetterAdapterCreation() {
        KafkaTemplate<String, String> template = mock(KafkaTemplate.class);
        EventPublisherPort publisher = mock(EventPublisherPort.class);
        ObjectMapper mapper = new ObjectMapper();
        EventPublisherLogger logger = mock(EventPublisherLogger.class);
        EventPublisherProperties props = mock(EventPublisherProperties.class);
        ConcurrentMessageListenerContainer<String, String> container = mock(ConcurrentMessageListenerContainer.class);

        when(props.getDeadLetterTopic()).thenReturn("dead-topic");

        KafkaDeadLetterAdapter adapter = config.kafkaDeadLetterAdapter(
                template, publisher, mapper, logger, props, container
        );

        assertNotNull(adapter);
    }

    // -------------------------------------------------------------
    // DEAD LETTER PORT
    // -------------------------------------------------------------
    @Test
    void testDeadLetterPortCreation() {
        KafkaDeadLetterAdapter adapter = mock(KafkaDeadLetterAdapter.class);

        DeadLetterPort port = config.deadLetterPort(adapter);

        assertNotNull(port);
        assertSame(adapter, port);
    }

    // -------------------------------------------------------------
    // DEAD LETTER CONSUMER PORT
    // -------------------------------------------------------------
    @Test
    void testDeadLetterConsumerPortCreation() {
        KafkaDeadLetterAdapter adapter = mock(KafkaDeadLetterAdapter.class);

        DeadLetterConsumerPort port = config.deadLetterConsumerPort(adapter);

        assertNotNull(port);
        assertSame(adapter, port);
    }

    // -------------------------------------------------------------
    // EVENT PUBLISHING SERVICE
    // -------------------------------------------------------------
    @Test
    void testEventPublishingServiceCreation() {
        EventPublisherPort publisher = mock(EventPublisherPort.class);
        DeadLetterPort deadLetter = mock(DeadLetterPort.class);
        RetryPolicy policy = new RetryPolicy(3, Duration.ofMillis(100));
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        EventPublishingService service =
                config.eventPublishingService(publisher, deadLetter, policy, logger);

        assertNotNull(service);
    }

    // -------------------------------------------------------------
    // DEAD LETTER RETRY SERVICE
    // -------------------------------------------------------------
    @Test
    void testDeadLetterRetryServiceCreation() {
        DeadLetterConsumerPort consumer = mock(DeadLetterConsumerPort.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        DeadLetterRetryService service = config.deadLetterRetryService(consumer, logger);

        assertNotNull(service);
    }

    // -------------------------------------------------------------
    // EXECUTOR SERVICE
    // -------------------------------------------------------------
    @Test
    void testExecutorServiceCreation() {
        ExecutorService executor = config.eventPublisherExecutorService();

        assertNotNull(executor);
    }

    // -------------------------------------------------------------
    // PUBLISH EVENT USE CASE
    // -------------------------------------------------------------
    @Test
    void testPublishEventUseCaseCreation() {
        ObjectMapper mapper = new ObjectMapper();
        EventPublishingService service = mock(EventPublishingService.class);
        ExecutorService executor = mock(ExecutorService.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventUseCase useCase =
                config.publishEventUseCase(mapper, service, executor, logger);

        assertNotNull(useCase);
    }

    // -------------------------------------------------------------
    // DEAD LETTER RETRY USE CASE
    // -------------------------------------------------------------
    @Test
    void testDeadLetterRetryUseCaseCreation() {
        DeadLetterRetryService service = mock(DeadLetterRetryService.class);

        DeadLetterRetryUseCase useCase = config.deadLetterRetryUseCase(service);

        assertNotNull(useCase);
    }

    // -------------------------------------------------------------
    // PUBLISH EVENT ASPECT
    // -------------------------------------------------------------
    @Test
    void testPublishEventAspectCreation() {
        PublishEventUseCase useCase = mock(PublishEventUseCase.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventAspect aspect = config.publishEventAspect(useCase, logger);

        assertNotNull(aspect);
    }
}
