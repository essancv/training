package com.viewnext.eventframework.adapter.in.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.service.SubscriptionRegistry;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.net.URI;

import java.util.concurrent.ConcurrentHashMap;
import com.viewnext.eventframework.application.port.in.security.AuthProvider;
import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;
import com.viewnext.eventframework.application.security.AuthRequest;
import com.viewnext.eventframework.application.security.UserContext;


/**
 * Handler principal de WebSocket.
 */
public class EventWebSocketHandler implements WebSocketHandler {

    private final SubscriptionRegistry registry;
    private final ObjectMapper objectMapper;
    private final AuthProvider authProvider;   // Identificación del usuario
    private final AuthorizationProvider authorizationProvider;   // Autorización

    private final Map<WebSocketSession, WebSocketClientConnection> connections = new ConcurrentHashMap<>();

    public EventWebSocketHandler(SubscriptionRegistry registry, ObjectMapper objectMapper,AuthProvider authProvider,AuthorizationProvider authorizationProvider) {
        this.registry = registry;
        this.objectMapper = objectMapper;
        this.authProvider = authProvider;
        this.authorizationProvider = authorizationProvider;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("🟢 WS Connected: " + session.getId());
        System.out.println(" WS Construimos AuthRequest");
        // 1. Construir AuthRequest
        AuthRequest request = buildAuthRequest(session);

        // 2. Resolver usuario
        UserContext user = authProvider.resolve(request);
        
        // (temporal) debug
        System.out.println("👤 UserContext: " + user.getUserId());

        WebSocketClientConnection connection =
                new WebSocketClientConnection(session, objectMapper,user);

        connections.put(session, connection);
/*
        System.out.println(" WS Construimos AuthRequest");
        // 1. Construir AuthRequest
        AuthRequest request = buildAuthRequest(session);

        // 2. Resolver usuario
        UserContext user = authProvider.resolve(request);

        // 3. Guardar en sesión
        session.getAttributes().put("userContext", user);

        // (temporal) debug
        System.out.println("👤 UserContext: " + user.getUserId());
*/
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        JsonNode json = objectMapper.readTree(message.getPayload().toString());

        String type = json.get("type").asText();

        WebSocketClientConnection connection = connections.get(session);

        switch (type) {
            case "SUBSCRIBE" -> {
                // Nuevo para autorización

                UserContext user = (UserContext) session.getAttributes().get("userContext");

                String topic = json.get("payload").get("topic").asText();
                System.out.println("📥 SUBSCRIBE " + topic);
                boolean allowed = authorizationProvider.canSubscribe(user, topic);
                if (!allowed) {
                    System.out.println("⛔ SUBSCRIBE DENIED for user=" + user.getUserId() + " topic=" + topic);

                    // opcional: enviar error al cliente
                    session.sendMessage(new TextMessage("""
                        {"type":"ERROR","message":"Not authorized for topic"}
                    """));

                    return;
                }
                registry.subscribe(topic, connection);
            }

            case "UNSUBSCRIBE" -> {
                String topic = json.get("payload").get("topic").asText();
                System.out.println("📤 UNSUBSCRIBE " + topic);

                registry.unsubscribe(topic, connection);
            }
        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("❌ WS ERROR: " + exception.getMessage());
        cleanup(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        System.out.println("🔴 WS Disconnected: " + session.getId());
        cleanup(session);
    }

    private void cleanup(WebSocketSession session) {
        WebSocketClientConnection connection = connections.remove(session);

        if (connection != null) {
            registry.removeConnection(connection);
            connection.close();
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private AuthRequest buildAuthRequest(WebSocketSession session) {

    Map<String, List<String>> headers = new HashMap<>();
    Map<String, List<String>> queryParams = new HashMap<>();

    // -------------------------
    // Headers
    // -------------------------
    session.getHandshakeHeaders().forEach((key, value) -> {
        headers.put(key, value);
    });

    // -------------------------
    // Query params
    // -------------------------
    URI uri = session.getUri();

    if (uri != null && uri.getQuery() != null) {
        String[] pairs = uri.getQuery().split("&");

        for (String pair : pairs) {
            String[] kv = pair.split("=");

            String key = kv[0];
            String value = kv.length > 1 ? kv[1] : "";

            queryParams
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(value);
        }
    }

    return new AuthRequest(headers, queryParams);
}
}