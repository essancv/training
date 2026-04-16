package com.viewnext.eventframework.application.port.out;

import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.application.security.UserContext;

/**
 * Puerto de salida que representa una conexión activa hacia un cliente externo.
 *
 * <p>
 * Esta interfaz abstrae el mecanismo de transporte (WebSocket, SSE, etc.),
 * permitiendo que la lógica de aplicación envíe eventos sin conocer detalles
 * de infraestructura.
 * </p>
 *
 * <p>
 * Responsabilidades:
 * <ul>
 *   <li>Identificar de forma única una conexión</li>
 *   <li>Enviar eventos al cliente</li>
 *   <li>Gestionar el cierre de la conexión</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementaciones típicas:
 * <ul>
 *   <li>WebSocketClientConnection</li>
 *   <li>SseClientConnection</li>
 * </ul>
 * </p>
 *
 * <p>
 * Este puerto forma parte de la arquitectura hexagonal y permite desacoplar
 * la lógica de negocio de los detalles de transporte.
 * </p>
 */
public interface ClientConnection {

    /**
     * Devuelve un identificador único de la conexión.
     *
     * <p>
     * Este identificador se utiliza para gestionar suscripciones,
     * trazabilidad y eliminación de conexiones.
     * </p>
     *
     * @return id único de la conexión
     */
    String getId();

    /**
     * Envía un evento al cliente.
     *
     * <p>
     * La implementación concreta se encargará de serializar el evento
     * y enviarlo a través del transporte correspondiente.
     * </p>
     *
     * @param event evento de dominio a enviar
     */
    void send(DomainEvent event);

    /**
     * Cierra la conexión con el cliente.
     *
     * <p>
     * Se utiliza para liberar recursos cuando el cliente se desconecta
     * o cuando se produce un error.
     * </p>
     */
    void close();

    /**
     * Devuelve el contexto de usuario asociado a la conexión.
     */
    UserContext getUserContext();
}