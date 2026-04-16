/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/config/EventFrameworkConfig.java
 */

package com.viewnext.eventframework.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.port.out.EventPublisher;
import com.viewnext.eventframework.application.service.ProcessConsumedEventUseCase;
import com.viewnext.eventframework.application.service.PublishEventUseCase;
import com.viewnext.eventframework.infrastructure.annotation.PublishEvent;
import com.viewnext.eventframework.infrastructure.consumer.*;
import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import com.viewnext.eventframework.infrastructure.logging.LoggingConfigProperties;
import com.viewnext.eventframework.infrastructure.logging.PayloadMasker;
import com.viewnext.eventframework.infrastructure.publisher.KafkaEventPublisher;
import com.viewnext.eventframework.infrastructure.serialization.EventDeserializer;
import com.viewnext.eventframework.infrastructure.serialization.EventSerializer;
import com.viewnext.eventframework.infrastructure.serialization.EventTypeRegistrar;
import com.viewnext.eventframework.infrastructure.serialization.EventTypeRegistry;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.viewnext.eventframework.application.service.SubscriptionRegistry;
import com.viewnext.eventframework.application.service.ExternalEventDispatcher;

import java.util.Map;

import com.viewnext.eventframework.infrastructure.publisher.PublishEventAspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
/**
 * Configuración principal del framework de eventos.
 *
 * <p>Registra todos los componentes necesarios para:
 * <ul>
 *   <li>Publicar eventos</li>
 *   <li>Consumir eventos</li>
 *   <li>Registrar consumidores anotados</li>
 *   <li>Gestionar logging</li>
 *   <li>Configurar serialización/deserialización</li>
 *   <li>Gestionar thread pool, reintentos y DLQ</li>
 * </ul>
 *
 * <p>Todos los beans pueden ser sobrescritos por la aplicación cliente
 * simplemente declarando un bean del mismo tipo.</p>
 */
import org.springframework.context.annotation.ComponentScan;
import com.viewnext.eventframework.infrastructure.security.PermitAllAuthorizationProvider;
import com.viewnext.eventframework.infrastructure.security.NoAuthProvider;
import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;
import com.viewnext.eventframework.application.port.in.security.AuthProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;



@AutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan("com.viewnext.eventframework")
public class EventFrameworkConfig {

    // -------------------------------------------------------------------------
    //  LOGGING
    // -------------------------------------------------------------------------

    @Bean
    public LoggingConfigProperties loggingConfigProperties() {
        return new LoggingConfigProperties();
    }

    @Bean
    public PayloadMasker payloadMasker(ObjectMapper objectMapper) {
        return new PayloadMasker(objectMapper);
    }

    @Bean
    public EventLogger eventLogger(
            LoggingConfigProperties config,
            PayloadMasker payloadMasker,
            ObjectMapper objectMapper
    ) {
        return new EventLogger(config, payloadMasker, objectMapper);
    }

    // -------------------------------------------------------------------------
    //  SERIALIZACIÓN / DESERIALIZACIÓN
    // -------------------------------------------------------------------------

    @Bean
    public EventSerializer eventSerializer(ObjectMapper objectMapper) {
        return new EventSerializer(objectMapper);
    }

/*
    @Bean
    public EventDeserializer eventDeserializer(
            ObjectMapper objectMapper,
            Map<String, Class<?>> eventTypes
    ) {
        // El mapa eventTypes debe ser proporcionado por la app cliente
        // o por un escáner automático (futuro)
        return new EventDeserializer(objectMapper, (Map) eventTypes);
    }
*/
    // -------------------------------------------------------------------------
    //  PUBLICACIÓN DE EVENTOS
    // -------------------------------------------------------------------------

    @Bean
    public EventPublisher eventPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            EventLogger logger
    ) {
        return new KafkaEventPublisher(kafkaTemplate, objectMapper, logger);
    }

    @Bean
    public PublishEventUseCase publishEventUseCase(EventPublisher publisher) {
        return new PublishEventUseCase(publisher);
    }

    // -------------------------------------------------------------------------
    //  CONSUMO DE EVENTOS
    // -------------------------------------------------------------------------

    @Bean
    public static ConsumerRegistry consumerRegistry() {
        return new ConsumerRegistry();
    }

    @Bean
    public static ConsumeEventScanner consumeEventScanner(ConsumerRegistry registry) {
        return new ConsumeEventScanner(registry);
    }


    @Bean
    public KafkaEventListener kafkaEventListener(
            ConsumerRegistry registry,
            ObjectMapper objectMapper,
            ProcessConsumedEventUseCase processUseCase,
            EventLogger logger
    ) {
        return new KafkaEventListener(registry, objectMapper, processUseCase, logger);
    }

    // -------------------------------------------------------------------------
    //  THREAD POOL + REINTENTOS + DLQ
    // -------------------------------------------------------------------------

/*
    @Bean
    public EventListenerContainer eventListenerContainer(
            KafkaEventListener listener,
            EventLogger logger,
            KafkaConsumer<String, String> kafkaConsumer
    ) {
        return new EventListenerContainer(
                listener,
                logger,
                10, // tamaño del thread pool (configurable)
                3,  // reintentos (configurable)
                record -> {
                    // Implementación simple de DLQ
                    // En producción, enviaríamos a un topic .DLQ
                    System.err.println("DLQ: " + record.value());
                }
        );
    }
    */

@Bean
public static EventTypeRegistry eventTypeRegistry() {
    return new EventTypeRegistry();
}

@Bean
public static EventTypeRegistrar eventTypeRegistrar(EventTypeRegistry registry) {
    return new EventTypeRegistrar(registry); 
}

@Bean
public EventDeserializer eventDeserializer(
        ObjectMapper objectMapper,
        EventTypeRegistry registry
) {
    return new EventDeserializer(objectMapper, registry.getTypes());
}

@Bean
public EventListenerContainer eventListenerContainer(
        KafkaEventListener listener,
        EventLogger logger,
        KafkaTemplate<String, String> kafkaTemplate
) {
    return new EventListenerContainer(
            listener,
            logger,
            10, // thread pool size (configurable)
            3,  // max retries (configurable)
            new KafkaDlqPublisher(kafkaTemplate, "events.dlq") // topic DLQ configurable
    );

}

    @Bean
    public PublishEventAspect publishEventAspect(
            PublishEventUseCase publishEventUseCase,
            EventLogger logger
    ) {
        return new PublishEventAspect(publishEventUseCase, logger);
    }

    @Bean
    public KafkaPollingService kafkaPollingService(
            KafkaConsumer<String, String> consumer,
            EventListenerContainer container,
            ConsumerRegistry registry
    ) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> Topics en registry al crear KafkaPollingService: " + registry.getAllTopics());
        return new KafkaPollingService(
                consumer,
                container,
                registry.getAllTopics() // debes añadir este método si no existe
        );
    }
    @Bean
    public KafkaConsumer<String, String> kafkaConsumer(
            org.springframework.kafka.core.ConsumerFactory<String, String> consumerFactory
    ) {
        return (KafkaConsumer<String, String>) consumerFactory.createConsumer();
    }

    @Bean
    public SubscriptionRegistry subscriptionRegistry() {
        return new SubscriptionRegistry();
    }

    @Bean
    public ExternalEventDispatcher externalEventDispatcher(SubscriptionRegistry registry ,AuthorizationProvider authorizationProvider) {
        return new ExternalEventDispatcher(registry,authorizationProvider);
    }
    @Bean
    public ProcessConsumedEventUseCase processConsumedEventUseCase(ExternalEventDispatcher externalEventDispatcher) {
        return new ProcessConsumedEventUseCase(externalEventDispatcher);
    }


 // ---------------------------------------------------------------------
    // 🔐 AUTHENTICATION
    // ---------------------------------------------------------------------

    /**
     * Proveedor de autenticación por defecto.
     *
     * <p>
     * Si la aplicación no define uno, se utilizará un proveedor que no aplica
     * autenticación (usuario anónimo).
     * </p>
     */
    @Bean
    @ConditionalOnMissingBean(AuthProvider.class)
    public AuthProvider authProvider() {
        return new NoAuthProvider();
    }

    // ---------------------------------------------------------------------
    // 🔐 AUTHORIZATION
    // ---------------------------------------------------------------------

    /**
     * Proveedor de autorización por defecto.
     *
     * <p>
     * Permite todas las operaciones (suscripción y recepción de eventos).
     * </p>
     */
    @Bean
    @ConditionalOnMissingBean(AuthorizationProvider.class)
    public AuthorizationProvider authorizationProvider() {
        return new PermitAllAuthorizationProvider();
    }

}
