package com.example.kafka.producer;

import com.example.kafka.request.Payload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${kafka.topicName}")
    private String topicName;

    public void sendMessage(Payload payload) {
        kafkaTemplate.send(topicName, payload);
        System.out.println("Produced message: " + payload.toString());
    }
}

