/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/application/port/out/EventPublisher.java
 */

package com.viewnext.eventframework.application.port.out;

import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Puerto de salida responsable de la publicación de eventos en un broker externo.
 *
 * <p>Este contrato define cómo la capa de aplicación espera que se publiquen
 * los eventos, sin conocer detalles de implementación como Kafka, RabbitMQ
 * u otros sistemas de mensajería.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Publicar un evento de dominio en un topic concreto, delegando en la
 * infraestructura la serialización, logging, reintentos y envío real.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       La interfaz solo define el contrato de publicación de eventos.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Nuevas implementaciones (Kafka, RabbitMQ, MockPublisher…) pueden añadirse
 *       sin modificar esta interfaz.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Cualquier implementación puede sustituir a otra sin romper el sistema.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       La interfaz es mínima y no obliga a implementar métodos innecesarios.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       La aplicación depende de esta abstracción, no de detalles concretos del broker.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso desde la aplicación</h2>
 *
 * <pre>{@code
 * @Service
 * public class SystemStatusNotifier {
 *
 *     private final EventPublisher eventPublisher;
 *
 *     public SystemStatusNotifier(EventPublisher eventPublisher) {
 *         this.eventPublisher = eventPublisher;
 *     }
 *
 *     public void notifyOk() {
 *         SystemStatusEvent event =
 *             new SystemStatusEvent(1, "OK", "El sistema está operativo");
 *
 *         eventPublisher.publish("system-status", event);
 *     }
 * }
 * }</pre>
 *
 * <h2>Ejemplo de implementación en infraestructura (Kafka)</h2>
 *
 * <pre>{@code
 * @Component
 * public class KafkaEventPublisher implements EventPublisher {
 *
 *     @Override
 *     public void publish(String topic, DomainEvent event) {
 *         // Serialización, logging, envío a Kafka...
 *     }
 * }
 * }</pre>
 */
public interface EventPublisher {

    /**
     * Publica un evento de dominio en el topic indicado.
     *
     * @param topic Nombre del topic donde se enviará el evento.
     * @param event Evento de dominio a publicar.
     */
    void publish(String topic, DomainEvent event);
}
