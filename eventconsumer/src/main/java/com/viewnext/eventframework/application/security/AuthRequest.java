package com.viewnext.eventframework.application.security;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Representa una solicitud de autenticación abstracta utilizada por el framework
 * para resolver el {@link UserContext}.
 *
 * <p>
 * Esta clase encapsula la información relevante de una conexión (headers,
 * parámetros de query, etc.) de forma agnóstica al protocolo o tecnología
 * subyacente (HTTP, WebSocket, SSE).
 * </p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Proveer un modelo unificado que permita al {@code AuthProvider} extraer
 * credenciales y metadatos sin depender de clases específicas de infraestructura.
 * </p>
 *
 * <h2>Quién usa esta clase</h2>
 * <ul>
 *   <li><strong>WebSocketHandler:</strong> construye una instancia a partir de la conexión WS</li>
 *   <li><strong>SSEController:</strong> construye una instancia a partir de la request HTTP</li>
 *   <li><strong>AuthProvider:</strong> consume esta información para resolver el {@link UserContext}</li>
 * </ul>
 *
 * <h2>Diseño</h2>
 * <ul>
 *   <li><strong>Inmutable:</strong> todos los datos son de solo lectura</li>
 *   <li><strong>Agnóstico:</strong> no depende de clases de transporte</li>
 *   <li><strong>Flexible:</strong> permite múltiples valores por clave</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 *
 * <pre>{@code
 * String token = request.getFirstHeader("Authorization");
 * String topic = request.getFirstQueryParam("topic");
 * }</pre>
 */
public class AuthRequest {

    private final Map<String, List<String>> headers;
    private final Map<String, List<String>> queryParams;

    public AuthRequest(
            Map<String, List<String>> headers,
            Map<String, List<String>> queryParams
    ) {
        this.headers = headers != null ? Collections.unmodifiableMap(headers) : Collections.emptyMap();
        this.queryParams = queryParams != null ? Collections.unmodifiableMap(queryParams) : Collections.emptyMap();
    }

    /**
     * Devuelve todos los headers de la solicitud.
     *
     * @return mapa inmutable de headers
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * Devuelve todos los parámetros de query.
     *
     * @return mapa inmutable de parámetros
     */
    public Map<String, List<String>> getQueryParams() {
        return queryParams;
    }

    /**
     * Obtiene el primer valor de un header.
     *
     * @param name nombre del header
     * @return valor o null si no existe
     */
    public String getFirstHeader(String name) {
        List<String> values = headers.get(name);
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }

    /**
     * Obtiene el primer valor de un parámetro de query.
     *
     * @param name nombre del parámetro
     * @return valor o null si no existe
     */
    public String getFirstQueryParam(String name) {
        List<String> values = queryParams.get(name);
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }

    /**
     * Indica si existe un header.
     *
     * @param name nombre del header
     * @return true si existe
     */
    public boolean hasHeader(String name) {
        return headers.containsKey(name);
    }

    /**
     * Indica si existe un parámetro de query.
     *
     * @param name nombre del parámetro
     * @return true si existe
     */
    public boolean hasQueryParam(String name) {
        return queryParams.containsKey(name);
    }
}