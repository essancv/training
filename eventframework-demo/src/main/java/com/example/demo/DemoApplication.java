package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        System.out.println(">>> CONTEXTO CARGADO <<<");
        System.out.println("Beans cargados:");
        Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> name.toLowerCase().contains("publish"))
                .forEach(System.out::println);
        System.out.println(">>> ¿Existe EventFrameworkConfig? " + 
        context.containsBeanDefinition("com.viewnext.eventframework.infrastructure.config.EventFrameworkConfig"));
    }
@Autowired
private ApplicationContext context;

@PostConstruct
public void check() {
    System.out.println(">>> Beans de tipo EventFrameworkConfig: " +
        context.getBeansOfType(
            com.viewnext.eventframework.infrastructure.config.EventFrameworkConfig.class
        )
    );
}


@PostConstruct
public void checkKafka() {
    System.out.println(">>> KafkaTemplate beans: " +
        context.getBeansOfType(org.springframework.kafka.core.KafkaTemplate.class)
    );
}
}
