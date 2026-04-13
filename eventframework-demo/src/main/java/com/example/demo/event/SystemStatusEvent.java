package com.example.demo.event;

import com.viewnext.eventframework.domain.event.DomainEvent;
import java.time.Instant;
import java.util.UUID;


public class SystemStatusEvent implements DomainEvent {

    private int version;
    private String status;
    private String message;
    private String eventId;
    private Instant occurredOn;
    public SystemStatusEvent() {}

    public SystemStatusEvent(int version, String status, String message) {
        this.version = version;
        this.status = status;
        this.message = message;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
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
    public int getVersion() { return version; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
}
