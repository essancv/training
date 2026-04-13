package com.example.demo.service;

import com.example.demo.events.DemoEvent;
import com.viewnext.eventframework.infrastructure.annotation.PublishEvent;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @PublishEvent(topic = "demo-topic")
    public DemoEvent generarEvento() {
        return new DemoEvent("Evento generado automáticamente por @PublishEvent");
    }
    @PublishEvent(topic = "demo2-topic")
    public DemoEvent generarEventoDlq() {
        return new DemoEvent("Evento DLQ generado automáticamente por @PublishEvent");
    }

}
