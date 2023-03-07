package com.learning.sample.services;

import com.learning.sample.models.CustomMsg;
import com.learning.sample.repository.CustomMsgRepository;
import jakarta.websocket.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    CustomMsgRepository customMsgRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, CustomMsg> kafkaTemplateOther;

    public String topicName = "test-string";

    public String topicName1 = "test-json";
    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    public void sendJsonMessage(CustomMsg msg) {
        kafkaTemplateOther.send(topicName1, msg);
    }

    public void saveJson(CustomMsg msg) {
        customMsgRepository.insert(msg);
    }
}
