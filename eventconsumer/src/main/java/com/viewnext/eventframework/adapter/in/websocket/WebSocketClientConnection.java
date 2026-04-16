package com.viewnext.eventframework.adapter.in.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.port.out.ClientConnection;
import com.viewnext.eventframework.domain.event.DomainEvent;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.UUID;

import com.viewnext.eventframework.application.security.UserContext;

/**
 * Implementación de ClientConnection basada en WebSocket.
 */
public class WebSocketClientConnection implements ClientConnection {

    private final WebSocketSession session;
    private final ObjectMapper objectMapper;
    private final String id = UUID.randomUUID().toString();
    private final UserContext userContext;

    public WebSocketClientConnection(WebSocketSession session, ObjectMapper objectMapper,UserContext userContext) {
        this.session = session;
        this.objectMapper = objectMapper;
        this.userContext = userContext;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void send(DomainEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            session.sendMessage(new TextMessage(json));
        } catch (IOException e) {
            throw new RuntimeException("Error sending WS message", e);
        }
    }

    @Override
    public void close() {
        try {
            session.close();
        } catch (IOException ignored) {}
    }

    @Override
    public UserContext getUserContext() {
        return userContext;
    }
}