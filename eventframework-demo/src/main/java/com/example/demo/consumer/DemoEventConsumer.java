package com.example.demo.consumer;

import com.example.demo.events.DemoEvent;
import com.viewnext.eventframework.infrastructure.annotation.ConsumeEvent;
import org.springframework.stereotype.Component;

@Component
public class DemoEventConsumer {

    @ConsumeEvent(topic = "demo-topic" , eventType = DemoEvent.class)
    public void handle(DemoEvent event) {
        System.out.println(">>> CONSUMIDOR RECIBIÓ EVENTO: " + event.getMessage());
    }
    @ConsumeEvent(topic = "demo2-topic" , eventType = DemoEvent.class)
    public void handleDlq(DemoEvent event) {
        System.out.println(">>> CONSUMIDOR RECIBIÓ EVENTO: " + event.getMessage());
        throw new RuntimeException("Fallo forzado para probar la DLQ");
    }
}
