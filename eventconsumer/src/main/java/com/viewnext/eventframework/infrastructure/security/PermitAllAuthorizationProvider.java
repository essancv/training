package com.viewnext.eventframework.infrastructure.security;

import com.viewnext.eventframework.application.port.in.security.AuthorizationProvider;
import com.viewnext.eventframework.application.security.UserContext;
import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Implementación por defecto de {@link AuthorizationProvider} que permite
 * todas las operaciones sin aplicar restricciones.
 *
 * <p>
 * Esta clase garantiza que el framework funcione sin necesidad de configurar
 * reglas de autorización, permitiendo que cualquier usuario:
 * </p>
 * <ul>
 *   <li>Se suscriba a cualquier topic</li>
 *   <li>Reciba cualquier evento</li>
 * </ul>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Proveer una política de autorización permisiva que facilite el uso del
 * framework en entornos sin requisitos de seguridad o durante fases iniciales
 * de desarrollo.
 * </p>
 *
 * <h2>Quién usa esta clase</h2>
 * <ul>
 *   <li><strong>EventFrameworkConfig:</strong> como implementación por defecto</li>
 *   <li><strong>SubscriptionManager / Controller:</strong> al validar suscripciones</li>
 *   <li><strong>ExternalEventDispatcher:</strong> al filtrar eventos</li>
 * </ul>
 *
 * <h2>Comportamiento</h2>
 * <ul>
 *   <li>Siempre permite la suscripción a cualquier topic</li>
 *   <li>Siempre permite la recepción de cualquier evento</li>
 *   <li>No evalúa roles, permisos ni atributos</li>
 * </ul>
 *
 * <h2>Casos de uso</h2>
 * <ul>
 *   <li>Entornos de desarrollo</li>
 *   <li>Aplicaciones sin control de acceso</li>
 *   <li>Fase inicial de integración del framework</li>
 * </ul>
 *
 * <h2>Ejemplo</h2>
 *
 * <pre>{@code
 * boolean allowed = authorizationProvider.canSubscribe(user, "orders");
 * // always true
 * }</pre>
 */
public class PermitAllAuthorizationProvider implements AuthorizationProvider {

    /**
     * Permite la suscripción a cualquier topic.
     *
     * @param userContext contexto del usuario
     * @param topic       topic solicitado
     * @return siempre true
     */
    @Override
    public boolean canSubscribe(UserContext userContext, String topic) {
        return true;
    }

    /**
     * Permite la recepción de cualquier evento.
     *
     * @param userContext contexto del usuario
     * @param event       evento a enviar
     * @return siempre true
     */
    @Override
    public boolean canReceive(UserContext userContext, DomainEvent event) {
        return true;
    }
}