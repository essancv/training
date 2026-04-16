package com.viewnext.eventframework.adapter.in.sse;

import com.viewnext.eventframework.application.service.SubscriptionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.viewnext.eventframework.application.port.out.ClientConnection;
import org.springframework.web.bind.annotation.RequestParam;
import com.viewnext.eventframework.application.port.in.security.AuthProvider;
import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;
import com.viewnext.eventframework.application.security.AuthRequest;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.viewnext.eventframework.application.security.UserContext;
@RestController
public class SSEController {

    private final SubscriptionRegistry registry;
    private final AuthProvider authProvider;   // Identificación del usuario
    private final AuthorizationProvider authorizationProvider;   // Autorización

    public SSEController(SubscriptionRegistry registry, AuthProvider authProvider,AuthorizationProvider authorizationProvider) {
        this.registry = registry;
        this.authProvider = authProvider;
        this.authorizationProvider = authorizationProvider;
    }

    @GetMapping("/events/sse")
    public SseEmitter stream(@RequestParam("topic") String topic, HttpServletRequest request) {

        SseEmitter emitter = new SseEmitter(0L);  
        
        // ---------------------------------------------------------------------
        // 2. AUTH → construir request
        // ---------------------------------------------------------------------
        AuthRequest authRequest = buildAuthRequest(request);

        UserContext user = authProvider.resolve(authRequest);

        System.out.println("👤 SSE UserContext: " + user.getUserId());

         // ---------------------------------------------------------------------
        // 3. AUTHORIZATION → validar subscribe
        // ---------------------------------------------------------------------
        boolean allowed = authorizationProvider.canSubscribe(user, topic);

        if (!allowed) {
            System.out.println("⛔ SSE SUBSCRIBE DENIED user=" + user.getUserId());

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Not authorized for topic"
            );
        }

    // ---------------------------------------------------------------------
    // 4. Crear conexión (con UserContext 🔥)
    // ---------------------------------------------------------------------

        SSEClientConnection connection = new SSEClientConnection(emitter,user);

        System.out.println("🟢 SSE Connected: " + connection.getId() + " topic=" + topic);

        registry.subscribe(topic, connection);

        emitter.onCompletion(() -> {
            System.out.println("🔴 SSE Completed: " + connection.getId());
            registry.unsubscribe(topic, connection);
        });

        emitter.onTimeout(() -> {
            System.out.println("⏱️ SSE Timeout: " + connection.getId());
            registry.unsubscribe(topic, connection);
        });

        return emitter;
    }

    private AuthRequest buildAuthRequest(HttpServletRequest request) {

        Map<String, List<String>> headers = new HashMap<>();
        Map<String, List<String>> queryParams = new HashMap<>();

        // Headers
        Collections.list(request.getHeaderNames()).forEach(name -> {
            headers.put(name, Collections.list(request.getHeaders(name)));
        });

        // Query params
        request.getParameterMap().forEach((key, values) -> {
            queryParams.put(key, Arrays.asList(values));
        });

        return new AuthRequest(headers, queryParams);
    }
}