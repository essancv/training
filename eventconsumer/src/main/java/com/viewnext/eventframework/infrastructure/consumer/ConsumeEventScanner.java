/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/consumer/ConsumeEventScanner.java
 */

package com.viewnext.eventframework.infrastructure.consumer;

import com.viewnext.eventframework.infrastructure.annotation.ConsumeEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * Escáner de consumidores que detecta métodos anotados con {@link ConsumeEvent}
 * durante la inicialización del contexto Spring.
 *
 * <p>Registra cada método consumidor en el {@link ConsumerRegistry} para que
 * la infraestructura pueda invocarlo cuando lleguen eventos del topic
 * correspondiente.</p>
 *
 * <h2>Responsabilidad</h2>
 * <p>
 * Inspeccionar beans, detectar métodos anotados y registrarlos en el registro
 * centralizado de consumidores.
 * </p>
 *
 * <h2>Tareas internas</h2>
 * <ul>
 *   <li>Recorrer todos los beans del contexto</li>
 *   <li>Inspeccionar sus métodos públicos</li>
 *   <li>Detectar anotaciones {@link ConsumeEvent}</li>
 *   <li>Registrar el método en {@link ConsumerRegistry}</li>
 * </ul>
 */
public class ConsumeEventScanner implements BeanPostProcessor {

    private final ConsumerRegistry registry;

    public ConsumeEventScanner(ConsumerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();

        for (Method method : clazz.getMethods()) {
            ConsumeEvent annotation = method.getAnnotation(ConsumeEvent.class);

            if (annotation != null) {
                registry.registerConsumer(
                    annotation.topic(),
                    annotation.eventType(),
                    bean,
                    method
                );
            }
        }

        return bean;
    }
}
