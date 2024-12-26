package com.example.kafka.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Payload {
    private boolean toBeConsumed;
    private String message;
}

