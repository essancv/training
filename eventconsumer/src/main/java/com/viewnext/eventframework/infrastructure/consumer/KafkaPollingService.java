package com.viewnext.eventframework.infrastructure.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.context.SmartLifecycle;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaPollingService implements SmartLifecycle {

    private final KafkaConsumer<String, String> consumer;
    private final EventListenerContainer container;
    private final List<String> topics;

    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread pollingThread;

    public KafkaPollingService(
            KafkaConsumer<String, String> consumer,
            EventListenerContainer container,
            List<String> topics
    ) {
        this.consumer = consumer;
        this.container = container;
        this.topics = topics;
    }

    @Override
    public void start() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> KafkaPollingService.start() INVOCADO");

        // Evita arranques múltiples
        if (!running.compareAndSet(false, true)) {
            return;
        }

        // Suscripción a los topics
        consumer.subscribe(topics);

        // Hilo de polling
        pollingThread = new Thread(() -> {
            System.out.println(">>>>>>>>>>>>>>>>>>> KafkaPollingService: hilo de polling INICIADO");
            while (running.get()) {
                try {
                    ConsumerRecords<String, String> records =
                            consumer.poll(Duration.ofMillis(200));
//System.out.println(">>>>>>>>>>>>>>>>>>> KafkaPollingService: poll ejecutado");

/*
                    if (!records.isEmpty()) {
                        System.out.println(">>>>>>>>>>>>>>>>>>> KafkaPollingService: recibidos " + records.count() + " records");
                    } else {
                        System.out.println(">>>>>>>>>>>>>>>>>>> KafkaPollingService: no hay nuevos records");
                    }
*/
                    records.forEach(container::submit);

                } catch (org.apache.kafka.common.errors.WakeupException ex) {
                    // Wakeup es normal durante stop()
                    if (running.get()) {
                        throw ex;
                    }
                } catch (Exception ex) {
                    // Evita que el hilo muera silenciosamente
                    ex.printStackTrace();
                }
            }
        }, "kafka-polling-thread");

        pollingThread.setDaemon(true);
        pollingThread.start();
    }

    @Override
    public void stop() {
        if (!running.compareAndSet(true, false)) {
            return;
        }

        // Despertar al consumer para salir del poll()
        consumer.wakeup();

        try {
            if (pollingThread != null) {
                pollingThread.join(1000);
            }
        } catch (InterruptedException ignored) {}

        consumer.close();
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public int getPhase() {
        // Arrancar el último, cuando TODO el contexto está listo
        return Integer.MAX_VALUE;
    }
}
