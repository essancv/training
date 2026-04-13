package com.example.demo.consumer;

import com.example.demo.event.SystemStatusEvent;
import com.viewnext.eventframework.infrastructure.annotation.ConsumeEvent;
import org.springframework.stereotype.Component;

@Component
public class SystemStatusConsumer {

    @ConsumeEvent(topic = "system-status", eventType = SystemStatusEvent.class)
    public void onSystemStatus(SystemStatusEvent event) {
        System.out.println("Evento recibido: " + event.getStatus() + " - " + event.getMessage());
    }
}
