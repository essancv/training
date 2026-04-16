package com.viewnext.eventframework.application.port.in.security;

import com.viewnext.eventframework.application.security.AuthRequest;
import com.viewnext.eventframework.application.security.UserContext;

/**
 * Puerto de entrada responsable de resolver el contexto de usuario
 * a partir de una solicitud de conexión.
 *
 * <p>
 * Este componente actúa como punto de extensión (SPI) que permite integrar
 * distintos mecanismos de autenticación sin acoplar el framework a una
 * tecnología concreta (JWT, OAuth, API Key, etc.).
 * </p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Traducir la información de la conexión (headers, parámetros, etc.)
 * en un {@link UserContext} que será utilizado por el framework para:
 * </p>
 * <ul>
 *   <li>Identificar al cliente conectado</li>
 *   <li>Aplicar reglas de autorización</li>
 *   <li>Filtrar eventos en el dispatcher</li>
 * </ul>
 *
 * <h2>Quién usa esta interfaz</h2>
 * <ul>
 *   <li><strong>WebSocketHandler:</strong> al establecer una conexión WS</li>
 *   <li><strong>SSEController:</strong> al abrir una conexión SSE</li>
 * </ul>
 *
 * <h2>Quién la implementa</h2>
 * <ul>
 *   <li>Implementaciones en infraestructura (JWT, OAuth, etc.)</li>
 *   <li>Implementación por defecto: {@code NoAuthProvider}</li>
 * </ul>
 *
 * <h2>Comportamiento esperado</h2>
 * <ul>
 *   <li>Debe ser stateless</li>
 *   <li>No debe lanzar excepciones por errores de autenticación</li>
 *   <li>Debe devolver un {@code UserContext} válido (por ejemplo, anónimo)</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 *
 * <pre>{@code
 * UserContext user = authProvider.resolve(request);
 * }</pre>
 */
public interface AuthProvider {

    /**
     * Resuelve el contexto de usuario a partir de una solicitud de conexión.
     *
     * <p>
     * Este método es invocado durante el establecimiento de la conexión
     * (WebSocket o SSE) para identificar al cliente.
     * </p>
     *
     * @param request información de la conexión (headers, parámetros, etc.)
     * @return contexto de usuario (nunca null)
     */
    UserContext resolve(AuthRequest request);
}