package com.viewnext.eventframework.application.service;

import com.viewnext.eventframework.application.port.out.ClientConnection;
import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;
import com.viewnext.eventframework.application.security.UserContext;
import java.util.Set;

/**
 * Componente de aplicación encargado de distribuir eventos de dominio
 * hacia clientes externos (WebSocket, SSE, etc.).
 *
 * <p>
 * Actúa como puente entre:
 * </p>
 *
 * <pre>
 *   Sistema interno (@PublishEvent / @ConsumeEvent)
 *                ↓
 *      ExternalEventDispatcher
 *                ↓
 *      Clientes conectados (WS / SSE)
 * </pre>
 *
 * <p>
 * Responsabilidades:
 * <ul>
 *   <li>Recibir eventos del dominio</li>
 *   <li>Determinar el topic del evento</li>
 *   <li>Obtener los clientes suscritos a ese topic</li>
 *   <li>Enviar el evento a cada cliente</li>
 * </ul>
 * </p>
 *
 * <p>
 * No conoce detalles de transporte, solo trabaja con el puerto
 * {@link ClientConnection}.
 * </p>
 */
public class ExternalEventDispatcher {

    private final SubscriptionRegistry registry;
    private final AuthorizationProvider authorizationProvider;

    public ExternalEventDispatcher(SubscriptionRegistry registry , AuthorizationProvider authorizationProvider) {
        this.registry = registry;
        this.authorizationProvider = authorizationProvider;
    }

    /**
     * Distribuye un evento a todos los clientes suscritos a su topic.
     *
     * @param event evento de dominio
     */
    public void dispatch(String topic ,DomainEvent event) {

//        String topic = resolveTopic(event);

        Set<ClientConnection> clients = registry.getSubscribers(topic);

      System.out.println("📤 Dispatching to topic: " + topic);
      System.out.println("👥 clients found: " + clients.size());

        if (clients.isEmpty()) {
            return; // nadie suscrito → no hacemos nada
        }


        for (ClientConnection client : clients) {
            try {
                 UserContext user = client.getUserContext();

                // 🔐 AUTORIZACIÓN
                boolean allowed = authorizationProvider.canReceive(user, event);

                if (!allowed) {
                    System.out.println("⛔ SKIP user=" + user.getUserId());
                    continue;
                }

                client.send(event);
            } catch (Exception e) {
                // ⚠️ importante: no romper el flujo por un cliente
                client.close();
                registry.removeConnection(client);
            }
        }
    }

    /**
     * Resuelve el topic de un evento.
     *
     * <p>
     * En esta versión simple, usamos el nombre de la clase como topic.
     * </p>
     *
     * <p>
     * Ejemplo:
     * <pre>
     *   OrderCreatedEvent → "OrderCreatedEvent"
     * </pre>
     * </p>
     *
     * <p>
     * ⚠️ Esto se podrá mejorar más adelante usando anotaciones o metadata.
     * </p>
     *
     * @param event evento
     * @return topic asociado
     */
    private String resolveTopic(DomainEvent event) {
        return event.getClass().getSimpleName();
    }
}