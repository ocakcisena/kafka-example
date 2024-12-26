package com.example.kafka.controller;

import com.example.kafka.request.Payload;
import com.example.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Payload payload) {
        kafkaProducer.sendMessage(payload);
        return "Message Sent!";
    }
}

