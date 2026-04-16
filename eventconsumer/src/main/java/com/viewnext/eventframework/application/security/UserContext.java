package com.viewnext.eventframework.application.security;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Representa el contexto de seguridad de un cliente conectado al sistema de eventos.
 *
 * <p>
 * Esta clase encapsula la identidad del usuario y sus características relevantes
 * para la autorización y distribución de eventos, sin depender de ningún mecanismo
 * concreto de autenticación (JWT, OAuth, API Key, etc.).
 * </p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Proveer una representación agnóstica y estable del usuario que permita al framework:
 * </p>
 * <ul>
 *   <li>Identificar al cliente conectado</li>
 *   <li>Aplicar reglas de autorización sobre suscripciones y eventos</li>
 *   <li>Transportar información contextual (tenant, región, etc.)</li>
 * </ul>
 *
 * <h2>Quién usa esta clase</h2>
 * <ul>
 *   <li><strong>AuthProvider:</strong> construye instancias de {@code UserContext}</li>
 *   <li><strong>Connection (WS/SSE):</strong> almacena el contexto asociado a cada cliente</li>
 *   <li><strong>AuthorizationProvider:</strong> evalúa permisos basados en este contexto</li>
 *   <li><strong>ExternalEventDispatcher:</strong> filtra eventos según el usuario</li>
 * </ul>
 *
 * <h2>Diseño</h2>
 * <ul>
 *   <li><strong>Inmutable:</strong> todos los campos son finales y no modificables</li>
 *   <li><strong>Extensible:</strong> permite atributos arbitrarios mediante un mapa</li>
 *   <li><strong>Agnóstico:</strong> no depende de frameworks de seguridad externos</li>
 * </ul>
 *
 * <h2>Ejemplos de uso</h2>
 *
 * <pre>{@code
 * UserContext anonymous = UserContext.anonymous();
 *
 * UserContext user = UserContext.builder()
 *     .userId("123")
 *     .roles(Set.of("ADMIN"))
 *     .permissions(Set.of("topic:orders"))
 *     .attribute("tenant", "acme")
 *     .build();
 * }</pre>
 */
public class UserContext {

    private final String userId;
    private final Set<String> roles;
    private final Set<String> permissions;
    private final Map<String, Object> attributes;

    private UserContext(Builder builder) {
        this.userId = builder.userId;
        this.roles = Collections.unmodifiableSet(builder.roles);
        this.permissions = Collections.unmodifiableSet(builder.permissions);
        this.attributes = Collections.unmodifiableMap(builder.attributes);
    }

    /**
     * Devuelve el identificador único del usuario.
     *
     * @return identificador del usuario (nunca null)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Devuelve los roles asociados al usuario.
     *
     * @return conjunto inmutable de roles (nunca null)
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Devuelve los permisos asociados al usuario.
     *
     * <p>
     * Estos permisos pueden ser utilizados por el {@code AuthorizationProvider}
     * para decidir si un usuario puede suscribirse o recibir eventos de un topic.
     * </p>
     *
     * @return conjunto inmutable de permisos (nunca null)
     */
    public Set<String> getPermissions() {
        return permissions;
    }

    /**
     * Devuelve atributos adicionales del usuario.
     *
     * <p>
     * Permite extender el contexto con información arbitraria como:
     * tenant, región, identificadores externos, etc.
     * </p>
     *
     * @return mapa inmutable de atributos (nunca null)
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Obtiene un atributo concreto del contexto.
     *
     * @param key clave del atributo
     * @return valor asociado o null si no existe
     */
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * Crea un {@code UserContext} anónimo.
     *
     * <p>
     * Este contexto se utiliza cuando no existe autenticación o cuando el
     * {@code AuthProvider} no puede resolver la identidad del usuario.
     * </p>
     *
     * @return instancia de usuario anónimo
     */
    public static UserContext anonymous() {
        return builder()
                .userId("anonymous")
                .build();
    }

    /**
     * Crea un builder para construir instancias de {@code UserContext}.
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder para {@link UserContext}.
     */
    public static class Builder {
        private String userId = "anonymous";
        private Set<String> roles = Collections.emptySet();
        private Set<String> permissions = Collections.emptySet();
        private Map<String, Object> attributes = Collections.emptyMap();

        /**
         * Establece el identificador del usuario.
         *
         * @param userId identificador único
         * @return builder
         */
        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Establece los roles del usuario.
         *
         * @param roles conjunto de roles
         * @return builder
         */
        public Builder roles(Set<String> roles) {
            this.roles = roles != null ? roles : Collections.emptySet();
            return this;
        }

        /**
         * Establece los permisos del usuario.
         *
         * @param permissions conjunto de permisos
         * @return builder
         */
        public Builder permissions(Set<String> permissions) {
            this.permissions = permissions != null ? permissions : Collections.emptySet();
            return this;
        }

        /**
         * Establece los atributos adicionales del usuario.
         *
         * @param attributes mapa de atributos
         * @return builder
         */
        public Builder attributes(Map<String, Object> attributes) {
            this.attributes = attributes != null ? attributes : Collections.emptyMap();
            return this;
        }

        /**
         * Añade un atributo individual al contexto.
         *
         * @param key clave
         * @param value valor
         * @return builder
         */
        public Builder attribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }

        /**
         * Construye una instancia inmutable de {@link UserContext}.
         *
         * @return nueva instancia
         */
        public UserContext build() {
            return new UserContext(this);
        }
    }
}