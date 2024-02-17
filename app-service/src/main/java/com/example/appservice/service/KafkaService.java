package com.example.appservice.service;

import com.example.appservice.entity.App;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageUserTopic(String message) {

        log.info("[ KafkaProducer ] Send Message : " + message);
        kafkaTemplate.send("user_topic", message);
    }
}
