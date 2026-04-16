package com.viewnext.eventframework.adapter.in.sse;

import com.viewnext.eventframework.application.port.out.ClientConnection;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.viewnext.eventframework.domain.event.DomainEvent;

import java.io.IOException;
import java.util.UUID;
import java.util.Map;

import com.viewnext.eventframework.application.security.UserContext;

public class SSEClientConnection implements ClientConnection {

    private final String id = UUID.randomUUID().toString();
    private final SseEmitter emitter;
    private final UserContext userContext;

    public SSEClientConnection(SseEmitter emitter,UserContext userContext) {
        this.emitter = emitter;
        this.userContext = userContext;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void send(DomainEvent event) {
        try {

            // Ajustar mensaje al cliente web que espera { "type": "EVENT", "payload": {...} }
                Map<String, Object> msg = Map.of(
                "type", "EVENT",
                "payload", event
            );
            emitter.send(SseEmitter.event()
                .name("EVENT")
                .data(event));
        } catch (IOException e) {
            close();
        }
    }

    @Override
    public void close() {
        emitter.complete();
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    @Override
    public UserContext getUserContext() {
        return userContext;
    }
}   
