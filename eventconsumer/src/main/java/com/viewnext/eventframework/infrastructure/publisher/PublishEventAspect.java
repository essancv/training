/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/publisher/PublishEventAspect.java
 */

package com.viewnext.eventframework.infrastructure.publisher;

import com.viewnext.eventframework.application.service.PublishEventUseCase;
import com.viewnext.eventframework.domain.event.DomainEvent;
import com.viewnext.eventframework.infrastructure.annotation.PublishEvent;
import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspecto AOP encargado de interceptar métodos anotados con {@link PublishEvent}
 * para publicar automáticamente el evento devuelto por el método.
 *
 * <p>Este componente actúa como puente entre la lógica de negocio y la
 * infraestructura de publicación, delegando en el caso de uso
 * {@link PublishEventUseCase}.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Interceptar métodos anotados, obtener el evento devuelto, registrar logs,
 * delegar en el caso de uso y manejar errores.
 * </p>
 *
 * <h2>Tareas internas del aspecto</h2>
 * <ul>
 *   <li>Registrar inicio de publicación</li>
 *   <li>Ejecutar el método original</li>
 *   <li>Validar que el retorno es un {@link DomainEvent}</li>
 *   <li>Registrar logs del evento</li>
 *   <li>Delegar en {@link PublishEventUseCase}</li>
 *   <li>Manejar errores y registrar trazas</li>
 * </ul>
 */
@Aspect
public class PublishEventAspect {

    private final PublishEventUseCase publishEventUseCase;
    private final EventLogger logger;

    public PublishEventAspect(PublishEventUseCase publishEventUseCase, EventLogger logger) {
        this.publishEventUseCase = publishEventUseCase;
        this.logger = logger;
    }

    /**
     * Intercepta métodos anotados con {@link PublishEvent}.
     */
    @Around("@annotation(publishEvent)")
    public Object intercept(ProceedingJoinPoint joinPoint, PublishEvent publishEvent) throws Throwable {

        String topic = publishEvent.topic();

        // ---------------------------------------------------------------------
        // 1. Ejecutar el método original
        // ---------------------------------------------------------------------
        Object result = joinPoint.proceed();

        if (!(result instanceof DomainEvent event)) {
            throw new IllegalStateException(
                "El método anotado con @PublishEvent debe devolver un DomainEvent"
            );
        }

        // ---------------------------------------------------------------------
        // 2. Logging del evento
        // ---------------------------------------------------------------------
        logger.logPublishStart(topic, event);

        try {
            // -----------------------------------------------------------------
            // 3. Delegar en el caso de uso
            // -----------------------------------------------------------------
            publishEventUseCase.publish(topic, event);

            // -----------------------------------------------------------------
            // 4. Log de éxito
            // -----------------------------------------------------------------
            logger.logPublishSuccess(topic, event);

        } catch (Exception ex) {
            // -----------------------------------------------------------------
            // 5. Log de error
            // -----------------------------------------------------------------
            logger.logPublishError(topic, event, ex);
            throw ex;
        }

        return result;
    }
}
