package org.example.nutssubscriber.api;

import org.example.nutssubscriber.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MessageService messageService;

    public MessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @RabbitListener(queues = "nutsQueue")
    public void receive(String message) {
        System.out.println("Received message from RabbitMQ: " + message);
        messageService.processMessage(message);
    }
}
