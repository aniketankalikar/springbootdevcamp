package com.advaya.springbootkafka.kafka;

import com.advaya.springbootkafka.dtos.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JSONKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONKafkaProducer.class);


    private KafkaTemplate<String,User> kafkaTemplate;

    public JSONKafkaProducer(KafkaTemplate kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data)
    {
        LOGGER.info("Message sent %s",data.toString());
        Message<User> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "jsonMessageTopic")
                .build();

        kafkaTemplate.send(message);
    }
}
