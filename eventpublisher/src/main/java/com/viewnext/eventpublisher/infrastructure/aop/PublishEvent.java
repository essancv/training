package com.viewnext.eventpublisher.infrastructure.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotación para marcar métodos cuyos valores de retorno
 * deben publicarse automáticamente como eventos en Kafka.
 *
 * El evento se publicará únicamente si el método finaliza
 * sin lanzar excepción y el valor de retorno no es null.
 */
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface PublishEvent {

    /**
     * Nombre del topic de Kafka donde se publicará el evento.
     *
     * @return nombre del topic
     */
    String topic();
}
