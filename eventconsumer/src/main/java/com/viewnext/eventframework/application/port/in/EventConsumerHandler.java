/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/application/port/in/EventConsumerHandler.java
 */

package com.viewnext.eventframework.application.port.in;

import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Puerto de entrada responsable de procesar eventos de dominio consumidos desde el broker.
 *
 * <p>Este contrato permite que la infraestructura invoque lógica de negocio
 * sin acoplarse a implementaciones concretas ni a detalles del broker
 * (Kafka, RabbitMQ, etc.).</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Definir cómo debe procesarse un evento deserializado y validado,
 * delegando en la infraestructura el logging, la deserialización,
 * los reintentos y la gestión de errores.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       La interfaz solo define el contrato de manejo de eventos.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Nuevos manejadores pueden añadirse sin modificar esta interfaz.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Cualquier implementación puede sustituir a otra sin romper el sistema.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       La interfaz es mínima y no obliga a implementar métodos innecesarios.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       La infraestructura depende de esta abstracción, no de implementaciones concretas.</li>
 * </ul>
 *
 * <h2>Relación con el sistema de logging</h2>
 * <p>
 * El componente de infraestructura registrará automáticamente:
 * </p>
 * <ul>
 *   <li>Inicio del consumo del evento</li>
 *   <li>Tipo del evento</li>
 *   <li>Payload (o payload enmascarado)</li>
 *   <li>Resultado del procesamiento</li>
 *   <li>Errores y reintentos</li>
 * </ul>
 *
 * <p>El manejador solo debe centrarse en la lógica de negocio.</p>
 *
 * <h2>Ejemplo de implementación</h2>
 *
 * <pre>{@code
 * public class SystemStatusEventHandler
 *         implements EventConsumerHandler<SystemStatusEvent> {
 *
 *     @Override
 *     public void handle(SystemStatusEvent event) {
 *         // Lógica de negocio
 *         System.out.println("Estado recibido: " + event.getStatus());
 *     }
 * }
 * }</pre>
 *
 * <h2>Ejemplo de uso con anotación @ConsumeEvent</h2>
 *
 * <pre>{@code
 * @ConsumeEvent(
 *     topic = "system-status",
 *     eventType = SystemStatusEvent.class
 * )
 * public void onSystemStatus(SystemStatusEvent event) {
 *     // Lógica de negocio
 * }
 * }</pre>
 *
 * @param <T> Tipo concreto de evento que se desea manejar.
 */
public interface EventConsumerHandler<T extends DomainEvent> {

    /**
     * Procesa un evento de dominio recibido desde la infraestructura.
     *
     * @param event Evento deserializado y validado.
     */
    void handle(T event);
}
