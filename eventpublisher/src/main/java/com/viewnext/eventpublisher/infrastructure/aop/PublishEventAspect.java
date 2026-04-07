package com.viewnext.eventpublisher.infrastructure.aop;

import com.viewnext.eventpublisher.application.service.PublishEventUseCase;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspecto que intercepta métodos anotados con @PublishEvent
 * y publica automáticamente el valor de retorno como evento.
 */
@Aspect
@Component
public class PublishEventAspect {

    private final PublishEventUseCase publishEventUseCase;
    private final EventPublisherLogger logger;

    /**
     * Crea el aspecto de publicación automática.
     *
     * @param publishEventUseCase caso de uso de publicación
     * @param logger              logger dedicado del componente
     */
    public PublishEventAspect(PublishEventUseCase publishEventUseCase,
                              EventPublisherLogger logger) {
        this.publishEventUseCase = publishEventUseCase;
        this.logger = logger;
    }

    /**
     * Intercepta la ejecución de métodos anotados con @PublishEvent.
     *
     * @param pjp          join point del método
     * @param publishEvent anotación con la configuración del topic
     * @return valor de retorno original del método
     * @throws Throwable cualquier excepción lanzada por el método original
     */
    @Around("@annotation(publishEvent)")
    public Object aroundPublishEvent(ProceedingJoinPoint pjp, PublishEvent publishEvent) throws Throwable {
        System.out.println(" *************** Interceptando método anotado con @PublishEvent: " + pjp.getSignature().toShortString());
        Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable ex) {
            logger.error("Método anotado con @PublishEvent lanzó excepción. No se publicará evento. Método=%s",
                    pjp.getSignature().toShortString(), ex);
            throw ex;
        }

        if (result == null) {
            logger.info("Método anotado con @PublishEvent devolvió null. No se publicará evento. Método=%s",
                    pjp.getSignature().toShortString());
            return result;
        }

        String topic = publishEvent.topic();
        logger.info("Método anotado con @PublishEvent finalizó correctamente. Programando publicación en topic='%s'. Método=%s",
                topic, pjp.getSignature().toShortString());
        publishEventUseCase.publishAsync(topic, result);
        System.out.println(" *************** Finalizando interceptación del método anotado con @PublishEvent: " + pjp.getSignature().toShortString());

        return result;
    }
}
