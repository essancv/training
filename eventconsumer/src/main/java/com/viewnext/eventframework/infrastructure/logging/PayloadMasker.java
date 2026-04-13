/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/logging/PayloadMasker.java
 */

package com.viewnext.eventframework.infrastructure.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewnext.eventframework.domain.event.DomainEvent;

/**
 * Componente encargado de enmascarar el payload de un evento cuando
 * la configuración así lo requiere.
 *
 * <p>El objetivo es evitar que información sensible aparezca en los logs,
 * cumpliendo con requisitos de seguridad, auditoría o normativas.</p>
 *
 * <h2>Modo de funcionamiento</h2>
 * <p>
 * Si el enmascaramiento está activado:
 * <ul>
 *   <li>Se devuelve un JSON con todos los campos reemplazados por "***MASKED***"</li>
 *   <li>Se mantiene la estructura del objeto</li>
 *   <li>Se evita exponer datos sensibles</li>
 * </ul>
 * </p>
 *
 * <h2>Ejemplo de salida enmascarada</h2>
 *
 * <pre>{@code
 * {
 *   "type": "SystemStatusEvent",
 *   "version": "***MASKED***",
 *   "status": "***MASKED***",
 *   "message": "***MASKED***"
 * }
 * }</pre>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S:</strong> Solo se encarga de enmascarar payloads.</li>
 *   <li><strong>O:</strong> Puede ampliarse para reglas más complejas.</li>
 *   <li><strong>L:</strong> Sustituible por mocks en tests.</li>
 *   <li><strong>I:</strong> No obliga a implementar interfaces.</li>
 *   <li><strong>D:</strong> Depende de abstracciones (ObjectMapper), no de infraestructura.</li>
 * </ul>
 */
public class PayloadMasker {

    private final ObjectMapper objectMapper;

    public PayloadMasker(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Enmascara todos los campos del evento.
     *
     * @param event Evento original.
     * @return JSON con todos los valores reemplazados por "***MASKED***".
     */
    public String mask(DomainEvent event) {
        try {
            // Convertimos el evento a un mapa genérico
            var tree = objectMapper.valueToTree(event);

            // Reemplazamos todos los valores por "***MASKED***"
            tree.fields().forEachRemaining(entry -> entry.setValue(
                objectMapper.getNodeFactory().textNode("***MASKED***")
            ));

            return objectMapper.writeValueAsString(tree);

        } catch (JsonProcessingException e) {
            return "\"<error-masking-payload>\"";
        }
    }
}
