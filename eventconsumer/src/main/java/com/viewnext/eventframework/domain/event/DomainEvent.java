/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/domain/event/DomainEvent.java
 */

package com.viewnext.eventframework.domain.event;

import java.time.Instant;
/**
 * Representa un evento de dominio dentro del sistema.
 *
 * <p>Esta interfaz actúa como contrato base para todos los eventos que
 * circulan por la arquitectura. Permite que la infraestructura de publicación
 * y consumo opere de forma genérica sin acoplarse a tipos concretos.</p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong> Esta interfaz solo define
 *       el concepto de "evento", sin añadir responsabilidades adicionales.</li>
 *   <li><strong>O (Open/Closed):</strong> Nuevos eventos pueden añadirse sin
 *       modificar esta interfaz.</li>
 *   <li><strong>L (Liskov Substitution):</strong> Cualquier implementación
 *       puede sustituir a otra sin romper el sistema.</li>
 *   <li><strong>I (Interface Segregation):</strong> La interfaz es mínima y no
 *       obliga a implementar métodos innecesarios.</li>
 *   <li><strong>D (Dependency Inversion):</strong> La infraestructura depende
 *       de esta abstracción, no de clases concretas.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 *
 * <pre>{@code
 * public class QaTestEvent implements DomainEvent {
 *     private final String status;
 *     private final String message;
 *
 *     public QaTestEvent(String status, String message) {
 *         this.status = status;
 *         this.message = message;
 *     }
 *
 *     public String getStatus() { return status; }
 *     public String getMessage() { return message; }
 * }
 * }</pre>
 *
 * <p>Este evento podrá ser publicado mediante la anotación
 * {@code @PublishEvent} y consumido mediante {@code @ConsumeEvent}.</p>
 */
public interface DomainEvent {
    // Interfaz de marcador (marker interface)
    String eventId();      // UUID del evento
    Instant occurredOn();  // Cuándo ocurrió
    String type();         // Nombre lógico del evento
}
