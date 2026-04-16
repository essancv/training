package com.viewnext.eventframework.infrastructure.security;

import com.viewnext.eventframework.application.port.in.security.AuthProvider;
import com.viewnext.eventframework.application.security.AuthRequest;
import com.viewnext.eventframework.application.security.UserContext;

/**
 * Implementación por defecto de {@link AuthProvider} que no aplica
 * ningún mecanismo de autenticación.
 *
 * <p>
 * Esta clase permite que el framework funcione sin necesidad de configurar
 * seguridad, devolviendo siempre un {@link UserContext} anónimo.
 * </p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Proveer un contexto de usuario válido cuando no existe autenticación,
 * garantizando que el flujo del sistema no se interrumpa.
 * </p>
 *
 * <h2>Quién usa esta clase</h2>
 * <ul>
 *   <li><strong>EventFrameworkConfig:</strong> como implementación por defecto</li>
 *   <li><strong>WebSocketHandler / SSEController:</strong> indirectamente al resolver el usuario</li>
 * </ul>
 *
 * <h2>Comportamiento</h2>
 * <ul>
 *   <li>Ignora completamente la información de la solicitud</li>
 *   <li>No valida credenciales</li>
 *   <li>Devuelve siempre un usuario anónimo</li>
 * </ul>
 *
 * <h2>Casos de uso</h2>
 * <ul>
 *   <li>Entornos de desarrollo</li>
 *   <li>Aplicaciones sin requisitos de seguridad</li>
 *   <li>Fase inicial de integración del framework</li>
 * </ul>
 *
 * <h2>Ejemplo</h2>
 *
 * <pre>{@code
 * UserContext user = authProvider.resolve(request);
 * // user.getUserId() == "anonymous"
 * }</pre>
 */
public class NoAuthProvider implements AuthProvider {

    /**
     * Devuelve un {@link UserContext} anónimo ignorando la solicitud.
     *
     * @param request solicitud de autenticación (no utilizada)
     * @return contexto de usuario anónimo (nunca null)
     */
    @Override
    public UserContext resolve(AuthRequest request) {
        return UserContext.anonymous();
    }
}