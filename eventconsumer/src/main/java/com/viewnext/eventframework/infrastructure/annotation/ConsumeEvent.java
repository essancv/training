/**
 * Estructura del directorio:
 * src/main/java/com/viewnext/eventframework/infrastructure/annotation/ConsumeEvent.java
 */

package com.viewnext.eventframework.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * Anotación que indica que el método anotado debe ejecutarse cuando se consuma
 * un evento del topic especificado.
 *
 * <p>Esta anotación es detectada por el escáner de consumidores, que registra
 * el método como manejador del tipo de evento indicado. La infraestructura se
 * encarga de deserializar el mensaje, aplicar reintentos, logging, enmascarado
 * de payload y delegar en el caso de uso de procesamiento.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Declarar de forma simple qué método debe ejecutarse al consumir un evento
 * de un topic concreto.
 * </p>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S (Single Responsibility):</strong>
 *       La anotación solo declara metadatos.</li>
 *
 *   <li><strong>O (Open/Closed):</strong>
 *       Nuevas funcionalidades pueden añadirse en el escáner o contenedor sin modificar esta anotación.</li>
 *
 *   <li><strong>L (Liskov Substitution):</strong>
 *       Cualquier método compatible puede ser un consumidor.</li>
 *
 *   <li><strong>I (Interface Segregation):</strong>
 *       No obliga a implementar interfaces adicionales.</li>
 *
 *   <li><strong>D (Dependency Inversion):</strong>
 *       La infraestructura depende de esta abstracción, no del método concreto.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
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
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsumeEvent {

    /**
     * Topic del que se consumirán los eventos.
     *
     * @return nombre del topic.
     */
    String topic();

    /**
     * Tipo concreto del evento que se espera recibir.
     *
     * <p>La infraestructura usará este tipo para deserializar el JSON
     * recibido desde Kafka.</p>
     *
     * @return clase del evento.
     */
    Class<?> eventType();
}
