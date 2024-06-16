package com.bank.kafkaproducer.controller;

import com.bank.kafkaproducer.service.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/msg")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessagePublisher messagePublisher;

    @PostMapping("/send")
    public String sendSimpleMessage(){
        messagePublisher.publishSimpleMsg();
        return "Done";
    }

    @PostMapping("/send/cbk")
    public String sendMessageWIthClbk(){
        messagePublisher.publishMsgWithCallbackFun();
        return "Done";
    }

    @PostMapping("/send/obj")
    public String sendObj(){
        messagePublisher.publishObj();
        return "Done";
    }
}
