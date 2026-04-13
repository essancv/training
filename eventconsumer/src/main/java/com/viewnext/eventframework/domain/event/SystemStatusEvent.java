/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/domain/event/SystemStatusEvent.java
 */

package com.viewnext.eventframework.domain.event;

import java.time.Instant;
import java.util.UUID;
/**
 * Representa un evento genérico de estado del sistema.
 *
 * <p>Incluye un campo {@code version} para permitir la evolución del evento
 * sin romper compatibilidad con consumidores antiguos.</p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S:</strong> Encapsula únicamente datos de un evento de estado.</li>
 *   <li><strong>O:</strong> Puede extenderse sin modificar esta clase.</li>
 *   <li><strong>L:</strong> Sustituible por cualquier otro {@link DomainEvent}.</li>
 *   <li><strong>I:</strong> Implementa solo la interfaz mínima necesaria.</li>
 *   <li><strong>D:</strong> La infraestructura depende de esta abstracción.</li>
 * </ul>
 */
public class SystemStatusEvent implements DomainEvent {

    private String eventId;
    private Instant occurredOn;

    private  int version;
    private  String status;
    private  String message;

    public SystemStatusEvent() {
        // Necesario para Jackson
    }

    /**
     * Crea un nuevo evento de estado del sistema.
     *
     * @param version Versión del evento para control de compatibilidad.
     * @param status  Estado general del evento (por ejemplo: "OK", "ERROR").
     * @param message Mensaje descriptivo asociado al estado.
     */
    public SystemStatusEvent(int version, String status, String message) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
        this.version = version;
        this.status = status;
        this.message = message;
    }

     @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String type() {
        return "SystemStatusEvent";
    }

    public int getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SystemStatusEvent{" +
                "version=" + version +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
