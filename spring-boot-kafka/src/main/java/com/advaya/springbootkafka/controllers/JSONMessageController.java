package com.advaya.springbootkafka.controllers;

import com.advaya.springbootkafka.dtos.User;
import com.advaya.springbootkafka.kafka.JSONKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JSONMessageController {

    private JSONKafkaProducer jsonKafkaProducer;

    public JSONMessageController(JSONKafkaProducer jsonKafkaProducer)
    {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User data)
    {
        jsonKafkaProducer.sendMessage(data);
        return ResponseEntity.ok("JSON Message Sent to Topic");
    }
}
