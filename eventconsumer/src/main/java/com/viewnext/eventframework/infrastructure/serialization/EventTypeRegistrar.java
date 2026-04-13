/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/serialization/EventTypeRegistrar.java
 */

package com.viewnext.eventframework.infrastructure.serialization;

import com.viewnext.eventframework.domain.event.DomainEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor que registra automáticamente todas las clases
 * que implementan DomainEvent.
 *
 * Se apoya en que los eventos estén definidos como beans o que
 * se registren explícitamente desde la app cliente.
 */
public class EventTypeRegistrar implements BeanPostProcessor {

    private final EventTypeRegistry registry;

    public EventTypeRegistrar(EventTypeRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof DomainEvent event) {
            registry.register(event.getClass());
        }

        return bean;
    }
}
