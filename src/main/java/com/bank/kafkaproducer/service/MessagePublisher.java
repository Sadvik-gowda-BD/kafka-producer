package com.bank.kafkaproducer.service;

import com.bank.kafkaproducer.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessagePublisher {

    private static final String topic1 = "my-topic-1";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public boolean publishSimpleMsg() {
        kafkaTemplate.send(topic1, "My simple me");
        return true;
    }

    public boolean publishMsgWithCallbackFun() {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic1, "Msg from callback");
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception occurred");
                throw new RuntimeException("Exceptio in publisher", ex);
            }
            System.out.println("Message sent successfully. Offset " + result.getRecordMetadata().offset());
        });
        return true;
    }

    public boolean publishObj() {
        StudentDto obj = new StudentDto("Virat", 10);
        kafkaTemplate.send(topic1, obj);
        return true;
    }
}
