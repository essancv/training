package com.viewnext.eventframework.application.port.in.security;

import com.viewnext.eventframework.application.security.UserContext;
import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Puerto de entrada responsable de evaluar si un usuario tiene permisos
 * para interactuar con el sistema de eventos.
 *
 * <p>
 * Este componente permite aplicar reglas de autorización sobre:
 * </p>
 * <ul>
 *   <li>Suscripción a topics</li>
 *   <li>Recepción de eventos</li>
 * </ul>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Determinar si un {@link UserContext} puede suscribirse a un topic
 * o recibir un evento concreto, permitiendo implementar políticas
 * de seguridad como control de acceso por roles, permisos o tenant.
 * </p>
 *
 * <h2>Quién usa esta interfaz</h2>
 * <ul>
 *   <li><strong>SubscriptionManager / Controller:</strong> valida suscripciones</li>
 *   <li><strong>ExternalEventDispatcher:</strong> filtra eventos antes de enviarlos</li>
 * </ul>
 *
 * <h2>Quién la implementa</h2>
 * <ul>
 *   <li>Implementaciones en infraestructura (RBAC, ABAC, multi-tenant, etc.)</li>
 *   <li>Implementación por defecto: {@code PermitAllAuthorizationProvider}</li>
 * </ul>
 *
 * <h2>Ejemplo</h2>
 *
 * <pre>{@code
 * if (authorizationProvider.canSubscribe(user, "orders")) {
 *     // permitir suscripción
 * }
 * }</pre>
 */
public interface AuthorizationProvider {

    /**
     * Indica si un usuario puede suscribirse a un topic.
     *
     * @param userContext contexto del usuario
     * @param topic       topic solicitado
     * @return true si está permitido
     */
    boolean canSubscribe(UserContext userContext, String topic);

    /**
     * Indica si un usuario puede recibir un evento concreto.
     *
     * <p>
     * Permite aplicar reglas más avanzadas como filtrado por tenant,
     * contenido del evento, etc.
     * </p>
     *
     * @param userContext contexto del usuario
     * @param event       evento a enviar
     * @return true si el usuario puede recibirlo
     */
    boolean canReceive(UserContext userContext, DomainEvent event);
}