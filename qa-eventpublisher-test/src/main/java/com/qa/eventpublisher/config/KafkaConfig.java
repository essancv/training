package com.qa.eventpublisher.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Configuración mínima de Kafka para QA.
 * Crea los topics necesarios si el broker lo permite (auto-create).
 */
@Configuration
@ConditionalOnProperty(name = "spring.kafka.admin.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConfig {

    @Bean
    @ConditionalOnMissingBean(name = "qaTopic")
    public NewTopic qaTopic() {
        return new NewTopic("qa-topic", 1, (short) 1);
    }

    @Bean
    @ConditionalOnMissingBean(name = "qaDeadLetterTopic")
    public NewTopic qaDeadLetterTopic() {
        return new NewTopic("qa-dead-letter", 1, (short) 1);
    }
}
