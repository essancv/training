/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/application/service/ProcessConsumedEventUseCase.java
 */

package com.viewnext.eventframework.application.service;

import com.viewnext.eventframework.application.port.in.EventConsumerHandler;
import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Caso de uso responsable de procesar un evento consumido desde el broker
 * antes de delegar en el manejador de eventos correspondiente.
 *
 * <p>Este caso de uso permite centralizar lógica de aplicación que debe
 * ejecutarse para todos los eventos consumidos, manteniendo el código
 * de los manejadores limpio y enfocado únicamente en la lógica de negocio.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Orquestar el procesamiento de un evento consumido, aplicando tareas
 * transversales antes o después de invocar al manejador.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       Este caso de uso se encarga únicamente de orquestar el procesamiento
 *       de eventos consumidos.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Se pueden añadir nuevas tareas transversales sin modificar el manejador
 *       ni la infraestructura.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Puede sustituirse por un mock en tests sin romper el sistema.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       Depende únicamente del puerto mínimo necesario: {@link EventConsumerHandler}.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       Depende de abstracciones del dominio, no de Kafka ni de infraestructura.</li>
 * </ul>
 *
 * <h2>Tareas que pueden incluirse en este caso de uso</h2>
 * <p>Este caso de uso puede incluir tareas transversales como:</p>
 *
 * <ul>
 *   <li><strong>Validaciones previas</strong>  
 *       - Validar estructura del evento  
 *       - Validar versión del evento  
 *       - Validar reglas de negocio básicas</li>
 *
 *   <li><strong>Auditoría</strong>  
 *       - Registrar que el evento ha sido recibido  
 *       - Registrar metadatos del evento (timestamp, topic, key, etc.)</li>
 *
 *   <li><strong>Enriquecimiento del evento</strong>  
 *       - Añadir datos adicionales  
 *       - Consultar servicios externos  
 *       - Transformar el evento antes del handler</li>
 *
 *   <li><strong>Aplicación de políticas</strong>  
 *       - Control de idempotencia  
 *       - Control de duplicados  
 *       - Control de versiones</li>
 *
 *   <li><strong>Post-procesamiento</strong>  
 *       - Registrar métricas  
 *       - Registrar logs adicionales  
 *       - Generar eventos derivados</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 *
 * <pre>{@code
 * @Component
 * public class SystemStatusEventListener {
 *
 *     private final ProcessConsumedEventUseCase processUseCase;
 *     private final SystemStatusEventHandler handler;
 *
 *     public SystemStatusEventListener(
 *         ProcessConsumedEventUseCase processUseCase,
 *         SystemStatusEventHandler handler
 *     ) {
 *         this.processUseCase = processUseCase;
 *         this.handler = handler;
 *     }
 *
 *     @ConsumeEvent(topic = "system-status", eventType = SystemStatusEvent.class)
 *     public void onEvent(SystemStatusEvent event) {
 *         processUseCase.process(event, handler);
 *     }
 * }
 * }</pre>
 */
public class ProcessConsumedEventUseCase {

    private final ExternalEventDispatcher dispatcher;

    /**
     * Constructor con inyección del dispatcher de eventos externos.
     *
     * @param dispatcher componente encargado de enviar eventos a clientes
     */
    public ProcessConsumedEventUseCase(ExternalEventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    /**
     * Procesa un evento consumido aplicando tareas transversales antes
     * de delegar en el manejador de eventos.
     *
     * @param event   Evento deserializado recibido desde el broker.
     * @param handler Manejador específico para el tipo de evento.
     * @param <T>     Tipo concreto del evento.
     */
    public <T extends DomainEvent> void process(String topic,T event, EventConsumerHandler<T> handler) {

        // ---------------------------------------------------------------------
        // 1. Validaciones previas (si se requieren)
        //    - Validar versión del evento
        //    - Validar campos obligatorios
        //    - Validar reglas de negocio básicas
        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------
        // 2. Auditoría (si se requiere)
        //    - Registrar recepción del evento
        //    - Registrar metadatos
        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------
        // 3. Enriquecimiento del evento (si se requiere)
        //    - Consultar servicios externos
        //    - Añadir información adicional
        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------
        // 4. Invocar al manejador de eventos
        // ---------------------------------------------------------------------
        handler.handle(event);

        // ---------------------------------------------------------------------
        // 5. Post-procesamiento: distribución a clientes externos
        // ---------------------------------------------------------------------
        // 🔥 NUEVO: enviar el evento a WebSocket / SSE
        dispatcher.dispatch(topic, event);


        // ---------------------------------------------------------------------
        // 6. Post-procesamiento (si se requiere)
        //    - Registrar métricas
        //    - Registrar logs adicionales
        //    - Generar eventos derivados
        // ---------------------------------------------------------------------
    }
}
