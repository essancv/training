package com.example.demo.producer;

import com.example.demo.event.SystemStatusEvent;
import com.viewnext.eventframework.infrastructure.annotation.PublishEvent;
import org.springframework.stereotype.Service;

@Service
public class SystemStatusProducer {

    @PublishEvent(topic = "system-status")
    public SystemStatusEvent publishOk() {
        return new SystemStatusEvent(1, "OK", "El sistema está operativo");
    }
}
