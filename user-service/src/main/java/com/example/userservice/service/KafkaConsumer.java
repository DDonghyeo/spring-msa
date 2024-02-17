package com.example.userservice.service;

import com.example.userservice.config.KafkaConsumerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "user_topic", groupId = "MSAGroupId")
    public void consumeUserTopic(@Payload String message, ConsumerRecordMetadata metadata) {
        log.info("[ KafkaConsumer] Message Received : " + message);
        log.info("[ KafkaConsumer] Partition : " + metadata.partition());

    }
}
