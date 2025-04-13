package org.example.nutssubscriber.repository;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime receivedAt;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public LocalDateTime getReceivedAt() { return receivedAt; }

    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }
}

