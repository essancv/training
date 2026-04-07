package com.qa.eventpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.viewnext.eventpublisher.infrastructure.config.EventPublisherProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


/**
 * Aplicación de prueba QA para validar el Event Publisher Kafka Starter.
 */
@SpringBootApplication
public class QaEventPublisherTestApplication {
    @Bean
    public CommandLineRunner testProps(EventPublisherProperties props) {
        return args -> {
            System.out.println("LOGGER NAME = " + props.getLoggerName());
        };
    }
    public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(QaEventPublisherTestApplication.class, args);

        System.out.println(">>> CONTEXTO CARGADO <<<");
        System.out.println("Beans cargados:");
        Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> name.toLowerCase().contains("publish"))
                .forEach(System.out::println);
    }
}
