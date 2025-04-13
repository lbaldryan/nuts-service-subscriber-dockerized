package org.example.nutssubscriber.repository;

import org.example.nutssubscriber.repository.MessageEntity;
import org.example.nutssubscriber.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
class MessageRepositoryTest {

    @Autowired
    private MessageRepository repository;

    @Test
    void testSaveAndRetrieveMessage() {
        MessageEntity message = new MessageEntity();
        message.setContent("Test message");
        message.setReceivedAt(LocalDateTime.now());

        MessageEntity saved = repository.save(message);
        Optional<MessageEntity> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Test message", found.get().getContent());
    }
}
