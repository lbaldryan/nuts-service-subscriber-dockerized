package org.example.nutssubscriber.service;

import org.example.nutssubscriber.repository.MessageEntity;
import org.example.nutssubscriber.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    private final MessageRepository repository = mock(MessageRepository.class);
    private final MessageService messageService = new MessageService(repository);

    @Test
    void testValidMessageIsSaved() {
        String content = "Validation of testing messages";
        messageService.processMessage(content);

        ArgumentCaptor<MessageEntity> captor = ArgumentCaptor.forClass(MessageEntity.class);
        verify(repository, times(1)).save(captor.capture());

        MessageEntity saved = captor.getValue();
        assertEquals(content, saved.getContent());
        assertNotNull(saved.getReceivedAt());
    }

    @Test
    void testBlankMessageIsNotSaved() {
        messageService.processMessage("");
        messageService.processMessage("   ");
        messageService.processMessage(null);

        verify(repository, never()).save(any());
    }
}
