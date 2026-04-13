package com.example.demo.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viewnext.eventframework.domain.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class DemoEvent implements DomainEvent {

    private String eventId;
    private Instant occurredOn;
    private String message;

    public DemoEvent() {
        // Necesario para Jackson
    }

    @JsonCreator
    public DemoEvent(
            @JsonProperty("message") String message
    ) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
        this.message = message;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String type() {
        return "DemoEvent";
    }

    public String getMessage() {
        return message;
    }
}
