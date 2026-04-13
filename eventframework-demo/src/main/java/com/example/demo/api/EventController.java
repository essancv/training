package com.example.demo.api;

/*
import com.viewnext.eventframework.application.service.PublishEventUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.events.DemoEvent;

@RestController
public class EventController {


    private final PublishEventUseCase publishEventUseCase;

    public EventController(PublishEventUseCase publishEventUseCase) {
        this.publishEventUseCase = publishEventUseCase;
    }

    @GetMapping("/publish")
    public String publish() {
        DemoEvent event = new DemoEvent("Hola desde el framework!");
        publishEventUseCase.publish("demo-topic", event);
        return "Evento enviado";
    }
}
*/

import com.example.demo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final DemoService demoService;

    public EventController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/publish")
    public String publish() {
        demoService.generarEvento(); // <-- Aquí se dispara @PublishEvent
        return "Evento enviado automáticamente por @PublishEvent";
    }
    
    @GetMapping("/dlq")
    public String dlq() {
        demoService.generarEventoDlq(); // <-- Aquí se dispara @PublishEvent
        return "Evento DLQ enviado automáticamente por @PublishEvent";
    }

}
