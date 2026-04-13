/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/logging/LoggingConfigProperties.java
 */

package com.viewnext.eventframework.infrastructure.logging;

/**
 * Propiedades de configuración del sistema de logging del framework de eventos.
 *
 * <p>Permite que la aplicación que usa el componente configure:
 * <ul>
 *   <li>Si el logging está habilitado</li>
 *   <li>Si el payload debe enmascararse</li>
 *   <li>Niveles de detalle adicionales (futuros)</li>
 * </ul>
 *
 * <p>Estas propiedades pueden mapearse desde:
 * <ul>
 *   <li>application.yml</li>
 *   <li>application.properties</li>
 *   <li>Variables de entorno</li>
 * </ul>
 *
 * <h2>Ejemplo de configuración en application.yml</h2>
 *
 * <pre>{@code
 * eventframework:
 *   logging:
 *     enabled: true
 *     mask-payload: false
 * }</pre>
 *
 * <h2>Principios SOLID aplicados</h2>
 * <ul>
 *   <li><strong>S:</strong> Solo encapsula configuración.</li>
 *   <li><strong>O:</strong> Puede ampliarse con nuevas propiedades sin romper nada.</li>
 *   <li><strong>L:</strong> Sustituible por mocks en tests.</li>
 *   <li><strong>I:</strong> No obliga a implementar interfaces.</li>
 *   <li><strong>D:</strong> La infraestructura depende de esta abstracción, no de valores hardcodeados.</li>
 * </ul>
 */
public class LoggingConfigProperties {

    /**
     * Indica si el logging del framework está habilitado.
     * Por defecto: true.
     */
    private boolean enabled = true;

    /**
     * Indica si el payload debe enmascararse en los logs.
     * Por defecto: false.
     */
    private boolean maskPayload = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isMaskPayload() {
        return maskPayload;
    }

    public void setMaskPayload(boolean maskPayload) {
        this.maskPayload = maskPayload;
    }
}
