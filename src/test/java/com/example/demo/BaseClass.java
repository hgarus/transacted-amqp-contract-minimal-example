package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@AutoConfigureMessageVerifier
@ExtendWith(SpringExtension.class)
public class BaseClass {

    @Autowired
    DemoApplication.MessageService messageService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    void triggerMethod() {
        messageService.sendMessage("foo");
    }
}
