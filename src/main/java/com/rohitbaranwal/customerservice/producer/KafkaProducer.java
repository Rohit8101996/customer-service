package com.rohitbaranwal.customerservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohitbaranwal.customerservice.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendMessage(Customer customer) {
        log.info(String.format("Message sent -> %s", customer.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String value = objectMapper.writeValueAsString(customer);
            Message<String> message = MessageBuilder
                    .withPayload(value)
                    .setHeader(KafkaHeaders.TOPIC, topicName)
                    .build();

            kafkaTemplate.send(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
