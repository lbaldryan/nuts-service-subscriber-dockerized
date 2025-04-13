package org.example.nutssubscriber.service;

import org.example.nutssubscriber.repository.MessageEntity;
import org.example.nutssubscriber.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void processMessage(String content) {
        if (content == null || content.isBlank()) {
            System.out.println("Received empty message. Skipping.");
            return;
        }

        MessageEntity message = new MessageEntity();
        message.setContent(content);
        message.setReceivedAt(LocalDateTime.now());

        repository.save(message);
        System.out.println("Message saved to database.");
    }
}

