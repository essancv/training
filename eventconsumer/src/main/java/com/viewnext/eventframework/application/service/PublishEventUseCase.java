/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/application/service/PublishEventUseCase.java
 */

package com.viewnext.eventframework.application.service;

import com.viewnext.eventframework.application.port.out.EventPublisher;
import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Caso de uso responsable de publicar eventos de dominio en un topic.
 *
 * <p>Este caso de uso actúa como orquestador entre la capa de aplicación
 * y el puerto de salida {@link EventPublisher}. No conoce detalles de
 * infraestructura como Kafka, serialización JSON o logging.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Publicar un evento delegando en el puerto de salida, garantizando que
 * la lógica de aplicación permanezca desacoplada del broker.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       Este caso de uso solo se encarga de publicar eventos.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Puede extenderse añadiendo validaciones sin modificar el puerto.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Puede sustituirse por un mock en tests sin romper el sistema.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       Depende únicamente del puerto mínimo necesario.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       Depende de la abstracción {@link EventPublisher}, no de Kafka.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso desde la aplicación</h2>
 *
 * <pre>{@code
 * @Service
 * public class SystemStatusNotifier {
 *
 *     private final PublishEventUseCase publishEventUseCase;
 *
 *     public SystemStatusNotifier(PublishEventUseCase publishEventUseCase) {
 *         this.publishEventUseCase = publishEventUseCase;
 *     }
 *
 *     public void notifyOk() {
 *         SystemStatusEvent event =
 *             new SystemStatusEvent(1, "OK", "El sistema está operativo");
 *
 *         publishEventUseCase.publish("system-status", event);
 *     }
 * }
 * }</pre>
 */
public class PublishEventUseCase {

    private final EventPublisher eventPublisher;

    /**
     * Crea un caso de uso para publicar eventos.
     *
     * @param eventPublisher Puerto de salida encargado de la publicación real.
     */
    public PublishEventUseCase(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Publica un evento en el topic indicado.
     *
     * @param topic Nombre del topic.
     * @param event Evento de dominio a publicar.
     */
    public void publish(String topic, DomainEvent event) {
        eventPublisher.publish(topic, event);
    }
}
