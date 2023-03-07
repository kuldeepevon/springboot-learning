package com.learning.sample.controllers;

import com.learning.sample.models.Category;
import com.learning.sample.models.CustomMsg;
import com.learning.sample.services.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Component
@RestController
@RequestMapping("message")
public class KafkaController {

    @Autowired
    KafkaService kafkaService;
    @KafkaListener(topics = "test-topic")
    public void listen(String in) {
        System.out.println(in);
    }

    @KafkaListener(topics = "test-json-topic", containerFactory = "kafkaListenerContainerFactoryWithJson")
    public void listenJson(CustomMsg msg) {
        System.out.println(msg);
        kafkaService.saveJson(msg);
    }

    @GetMapping("/str")
    public void sendStringMsg() {
        kafkaService.sendMessage("This is message from Java Spring boot");
//        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/json")
    public void sendJsonMsg() {
        CustomMsg msg = new CustomMsg();
        msg.setRid("1");
        msg.setName("Kuldeep");
        msg.setTs(new Date().getTime());
        kafkaService.sendJsonMessage(msg);
//        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
