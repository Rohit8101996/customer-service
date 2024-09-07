package com.rohitbaranwal.customerservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicJsonName;

    @Bean
    public NewTopic customerServiceJsonTopic() {
        return TopicBuilder.name(topicJsonName)
                .build();
    }

}
