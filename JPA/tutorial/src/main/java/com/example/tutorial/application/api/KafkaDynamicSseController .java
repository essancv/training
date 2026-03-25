package com.example.tutorial.application.api;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

@RestController
class KafkaDynamicSseController {

    @GetMapping(value = "/streamV2/kafka", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamKafka(
            @RequestParam String groupId,
            @RequestParam List<String> topics
    ) {

        return Flux.<String>create(emitter -> {

            // Crear consumer independiente
            Properties props = new Properties();
            props.put("bootstrap.servers", "172.17.0.1:9092");
            props.put("group.id", groupId);
            props.put("auto.offset.reset", "earliest");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(topics);

            // Hilo dedicado para este cliente
            Thread thread = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        var records = consumer.poll(Duration.ofMillis(200));
                        records.forEach(record ->
                                emitter.next(record.topic() + ": " + record.value())
                        );
                    }
                } catch (Exception e) {
                    emitter.error(e);
                } finally {
                    consumer.close();
                }
            });

            thread.start();

            // Cuando el cliente cierre la conexión SSE
            emitter.onDispose(() -> {
                thread.interrupt();
                consumer.wakeup();
            });

        }).map(msg -> ServerSentEvent.builder(msg).build());
    }
}
