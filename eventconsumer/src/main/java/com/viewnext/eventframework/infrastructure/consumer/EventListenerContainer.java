/**
 * Estructura del directorio:
 * src/main/java/com/tuempresa/eventframework/infrastructure/consumer/EventListenerContainer.java
 */

package com.viewnext.eventframework.infrastructure.consumer;

import com.viewnext.eventframework.infrastructure.logging.EventLogger;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contenedor encargado de ejecutar la lógica de consumo de eventos usando
 * un thread pool propio, aplicando reintentos y enviando mensajes a DLQ
 * cuando sea necesario.
 *
 * <p>Este componente desacopla la ejecución del listener Kafka del procesamiento
 * real del evento, permitiendo paralelismo configurable y resiliencia.</p>
 *
 * <h2>Responsabilidad</h2>
 * <ul>
 *   <li>Ejecutar el listener en un thread pool propio</li>
 *   <li>Aplicar reintentos configurables</li>
 *   <li>Enviar a DLQ si se agotan los reintentos</li>
 *   <li>Registrar logs de errores</li>
 * </ul>
 */
public class EventListenerContainer  {

    private final KafkaEventListener listener;
    private final EventLogger logger;

    private final ExecutorService executorService;
    private final int maxRetries;
    private final DlqPublisher dlqPublisher;

    public EventListenerContainer(
            KafkaEventListener listener,
            EventLogger logger,
            int threadPoolSize,
            int maxRetries,
            DlqPublisher dlqPublisher
    ) {
        this.listener = listener;
        this.logger = logger;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.maxRetries = maxRetries;
        this.dlqPublisher = dlqPublisher;
    }

    /**
     * Procesa un mensaje recibido desde Kafka.
     */
    public void submit(ConsumerRecord<String, String> record) {
        executorService.submit(() -> processWithRetries(record));
    }

    private void processWithRetries(ConsumerRecord<String, String> record) {
        int attempts = 0;

        while (attempts <= maxRetries) {
            try {
                listener.onMessage(record);
                return; // éxito
            } catch (Exception ex) {
                attempts++;

                if (attempts > maxRetries) {
                    logger.logConsumeError(record.topic(), null, ex);

                    // Enviar a DLQ
                    dlqPublisher.publishToDlq(record);
                    return;
                }

                // Espera incremental (backoff simple)
                try {
                    Thread.sleep(500L * attempts);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * Interfaz para publicar mensajes en la DLQ.
     */
    public interface DlqPublisher {
        void publishToDlq(ConsumerRecord<String, String> record);
    }
}
