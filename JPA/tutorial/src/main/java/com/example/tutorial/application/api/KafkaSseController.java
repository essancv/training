package com.example.tutorial.application.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Configuration
class KafkaStreamConfig {

    @Bean
    public Sinks.Many<String> kafkaSink() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

        // Configurar Kafka
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.17.0.1:9092");
        props.put("group.id", "sse-proxy");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of("user-created", "user-updated", "user-deleted"));

        // Hilo dedicado
        Thread kafkaThread = new Thread(() -> {
            try {
                while (true) {
                    var records = consumer.poll(Duration.ofMillis(200));
                    for (ConsumerRecord<String, String> record : records) {
                        sink.tryEmitNext(record.topic() + ": " + record.value());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        kafkaThread.setDaemon(true);
        kafkaThread.start();

        return sink;
    }
}

@RestController
class KafkaSseController {

    private final Sinks.Many<String> sink;

    public KafkaSseController(Sinks.Many<String> sink) {
        this.sink = sink;
    }

    @GetMapping(value = "/stream/kafka", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamKafka() {
        return sink.asFlux()
                .map(msg -> ServerSentEvent.builder(msg).build());
    }
}
