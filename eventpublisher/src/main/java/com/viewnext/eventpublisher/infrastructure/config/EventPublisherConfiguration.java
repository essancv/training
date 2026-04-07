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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.core.ConsumerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Configuración principal del componente Event Publisher.
 */
@Configuration
//@EnableConfigurationProperties(EventPublisherProperties.class)
public class EventPublisherConfiguration {

    /**
     * Crea el logger dedicado del componente.
     *
     * @param properties propiedades del componente
     * @return logger dedicado
     */
    @Bean
    @ConditionalOnMissingBean
    public EventPublisherLogger eventPublisherLogger(EventPublisherProperties properties) {
        return new EventPublisherLogger(properties.getLoggerName());
    }

    /**
     * Crea la política de reintentos.
     *
     * @param properties propiedades del componente
     * @return política de reintentos
     */
    @Bean
    @ConditionalOnMissingBean
    public RetryPolicy retryPolicy(EventPublisherProperties properties) {
        return new RetryPolicy(
                properties.getRetryMaxAttempts(),
                Duration.ofMillis(properties.getRetryBackoffMs())
        );
    }

    /**
     * Crea el adaptador de publicación en Kafka.
     *
     * @param kafkaTemplate plantilla de Kafka
     * @param logger        logger dedicado
     * @return adaptador de publicación
     */
    @Bean
    @ConditionalOnMissingBean
    public EventPublisherPort eventPublisherPort(KafkaTemplate<String, String> kafkaTemplate,
                                                 EventPublisherLogger logger) {
        return new KafkaEventPublisherAdapter(kafkaTemplate, logger);
    }

    /**
     * Crea el contenedor de escucha para el dead-letter topic.
     * (Ejemplo básico; en un proyecto real se integraría con la configuración global de Kafka).
     *
     * @param kafkaTemplate plantilla de Kafka
     * @param properties    propiedades del componente
     * @return contenedor de escucha
     */
   @Bean
    public ConcurrentMessageListenerContainer<String, String> deadLetterListenerContainer(
            ConsumerFactory<String, String> consumerFactory,
            EventPublisherProperties properties) {

        ContainerProperties containerProps =
                new ContainerProperties(properties.getDeadLetterTopic());

        // El listener se asignará DESPUÉS, desde el adaptador
        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps);
    }
    /**
     * Crea el adaptador de dead-letter.
     *
     * @param kafkaTemplate              plantilla de Kafka
     * @param eventPublisherPort         puerto de publicación
     * @param objectMapper               serializador JSON
     * @param logger                     logger dedicado
     * @param properties                 propiedades del componente
     * @param deadLetterListenerContainer contenedor de escucha
     * @return adaptador de dead-letter
     */
   @Bean
    @ConditionalOnMissingBean
    public KafkaDeadLetterAdapter kafkaDeadLetterAdapter(KafkaTemplate<String, String> kafkaTemplate,
                                                        EventPublisherPort eventPublisherPort,
                                                        ObjectMapper objectMapper,
                                                        EventPublisherLogger logger,
                                                        EventPublisherProperties properties,
                                                        ConcurrentMessageListenerContainer<String, String> deadLetterListenerContainer) {

        KafkaDeadLetterAdapter adapter = new KafkaDeadLetterAdapter(
                kafkaTemplate,
                eventPublisherPort,
                objectMapper,
                logger,
                properties.getDeadLetterTopic(),
                deadLetterListenerContainer
        );

        // Aquí el adaptador se registra como listener
        deadLetterListenerContainer.setupMessageListener(adapter);

        return adapter;
    }

    /**
     * Crea el puerto de dead-letter.
     *
     * @param adapter adaptador de dead-letter
     * @return puerto de dead-letter
     */
    @Bean
    @ConditionalOnMissingBean
    public DeadLetterPort deadLetterPort(KafkaDeadLetterAdapter adapter) {
        return adapter;
    }

    /**
     * Crea el puerto de consumo de dead-letter.
     *
     * @param adapter adaptador de dead-letter
     * @return puerto de consumo de dead-letter
     */
    @Bean
    @ConditionalOnMissingBean
    public DeadLetterConsumerPort deadLetterConsumerPort(KafkaDeadLetterAdapter adapter) {
        return adapter;
    }

    /**
     * Crea el servicio de publicación de eventos.
     *
     * @param eventPublisherPort puerto de publicación
     * @param deadLetterPort     puerto de dead-letter
     * @param retryPolicy        política de reintentos
     * @param logger             logger dedicado
     * @return servicio de publicación
     */
    @Bean
    @ConditionalOnMissingBean
    public EventPublishingService eventPublishingService(EventPublisherPort eventPublisherPort,
                                                         DeadLetterPort deadLetterPort,
                                                         RetryPolicy retryPolicy,
                                                         EventPublisherLogger logger) {
        return new EventPublishingService(eventPublisherPort, deadLetterPort, retryPolicy, logger);
    }

    /**
     * Crea el servicio de reproceso de dead-letter.
     *
     * @param deadLetterConsumerPort puerto de consumo de dead-letter
     * @param logger                 logger dedicado
     * @return servicio de reproceso
     */
    @Bean
    @ConditionalOnMissingBean
    public DeadLetterRetryService deadLetterRetryService(DeadLetterConsumerPort deadLetterConsumerPort,
                                                         EventPublisherLogger logger) {
        return new DeadLetterRetryService(deadLetterConsumerPort, logger);
    }

    /**
     * Crea el ejecutor para tareas asíncronas.
     *
     * @return executor service
     */
    @Bean
    @ConditionalOnMissingBean
    public ExecutorService eventPublisherExecutorService() {
        return Executors.newFixedThreadPool(4);
    }

    /**
     * Crea el caso de uso de publicación de eventos.
     *
     * @param objectMapper           serializador JSON
     * @param eventPublishingService servicio de dominio
     * @param executorService        ejecutor asíncrono
     * @param logger                 logger dedicado
     * @return caso de uso de publicación
     */
    @Bean
    @ConditionalOnMissingBean
    public PublishEventUseCase publishEventUseCase(ObjectMapper objectMapper,
                                                   EventPublishingService eventPublishingService,
                                                   ExecutorService executorService,
                                                   EventPublisherLogger logger) {
        return new PublishEventUseCase(objectMapper, eventPublishingService, executorService, logger);
    }

    /**
     * Crea el caso de uso de reproceso de dead-letter.
     *
     * @param deadLetterRetryService servicio de dominio
     * @return caso de uso de reproceso
     */
    @Bean
    @ConditionalOnMissingBean
    public DeadLetterRetryUseCase deadLetterRetryUseCase(DeadLetterRetryService deadLetterRetryService) {
        return new DeadLetterRetryUseCase(deadLetterRetryService);
    }

    /**
     * Crea el aspecto de publicación automática.
     *
     * @param publishEventUseCase caso de uso de publicación
     * @param logger              logger dedicado
     * @return aspecto AOP
     */
    @Bean
    @ConditionalOnMissingBean
    public PublishEventAspect publishEventAspect(PublishEventUseCase publishEventUseCase,
                                                 EventPublisherLogger logger) {
        return new PublishEventAspect(publishEventUseCase, logger);
    }
}
