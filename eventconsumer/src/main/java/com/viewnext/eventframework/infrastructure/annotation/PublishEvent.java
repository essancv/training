/**
 * Estructura del directorio:
 * src/main/java/com/viewnext/eventframework/infrastructure/annotation/PublishEvent.java
 */

package com.viewnext.eventframework.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * Anotación que indica que el método anotado debe publicar el evento devuelto
 * en el topic especificado.
 *
 * <p>Esta anotación es procesada por un aspecto AOP que intercepta la ejecución
 * del método, obtiene el valor devuelto (un {@code DomainEvent}) y delega en el
 * caso de uso de publicación para enviarlo al broker.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Declarar de forma simple e intuitiva que un método publica un evento,
 * sin acoplar la lógica de negocio a Kafka ni a la infraestructura.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       La anotación solo declara metadatos, no contiene lógica.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Nuevas funcionalidades pueden añadirse en el aspecto sin modificar esta anotación.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Cualquier método que devuelva un {@code DomainEvent} puede usar esta anotación.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       No obliga a implementar interfaces adicionales.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       La lógica de publicación depende del puerto {@code EventPublisher}, no de Kafka.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 *
 * <pre>{@code
 * @PublishEvent(topic = "system-status")
 * public SystemStatusEvent publishOk() {
 *     return new SystemStatusEvent(1, "OK", "Todo correcto");
 * }
 * }</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublishEvent {

    /**
     * Nombre del topic donde se publicará el evento.
     *
     * @return nombre del topic.
     */
    String topic();
}
