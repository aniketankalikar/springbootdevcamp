package com.advaya.springbootkafka.kafka;

import com.advaya.springbootkafka.dtos.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JSONKafkaConsumer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONKafkaConsumer.class);


    @KafkaListener(topics = "jsonMessageTopic",groupId = "mygroup")
    public void consume(User user)
    {
        LOGGER.info(String.format("JSON Message Recieved %s",user.toString()));
    }
}
