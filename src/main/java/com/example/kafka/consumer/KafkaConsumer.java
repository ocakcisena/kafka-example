package com.example.kafka.consumer;

import com.example.kafka.request.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topicName}", groupId = "${kafka.groupId}")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Payload payload = objectMapper.readValue(message, Payload.class);
        System.out.println("Consumed message: " + payload.toString());
    }
}

