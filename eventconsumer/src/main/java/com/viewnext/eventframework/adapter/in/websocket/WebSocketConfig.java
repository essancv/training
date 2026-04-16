package com.viewnext.eventframework.adapter.in.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.application.service.SubscriptionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import com.viewnext.eventframework.application.port.in.security.AuthProvider;
import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;


////// REVISAR SI ESTA CLASE ES NECESARIA .... PARECE QUE NO LO SERIA 
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SubscriptionRegistry registry;
    private final ObjectMapper objectMapper;
    private final AuthProvider authProvider;
    private final AuthorizationProvider authorizationProvider;

    public WebSocketConfig(SubscriptionRegistry registry, ObjectMapper objectMapper,AuthProvider authProvider,AuthorizationProvider authorizationProvider) {
        this.registry = registry;
        this.objectMapper = objectMapper;
        this.authProvider = authProvider;
        this.authorizationProvider = authorizationProvider;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registryWS) {
        registryWS.addHandler(
                new EventWebSocketHandler(registry, objectMapper,authProvider,authorizationProvider),
                "/events"
        ).setAllowedOrigins("*");
    }
}