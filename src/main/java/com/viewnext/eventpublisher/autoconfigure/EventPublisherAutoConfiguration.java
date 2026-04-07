package com.viewnext.eventpublisher.autoconfigure;

import com.viewnext.eventpublisher.infrastructure.config.EventPublisherConfiguration;
import com.viewnext.eventpublisher.infrastructure.config.EventPublisherProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Autoconfiguración del starter Event Publisher.
 * Se activa cuando existe KafkaTemplate en el classpath.
 */
@AutoConfiguration
@ConditionalOnClass(KafkaTemplate.class)
@EnableConfigurationProperties(EventPublisherProperties.class)
@Import(EventPublisherConfiguration.class)
public class EventPublisherAutoConfiguration {
}
