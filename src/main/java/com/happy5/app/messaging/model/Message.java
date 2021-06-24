package com.happy5.app.messaging.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long messageId;

    @Column(nullable = false, updatable = false)
    private Long groupId;

    @Column(nullable = false, updatable = false)
    private Long senderId;

    @Column(nullable = false, updatable = false)
    private Long recipientId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean seen;

    @Column(nullable = false, updatable = false)
    private LocalDateTime sentAt;

    // constructor - default
    public Message() {}

    // constructor - param
    public Message(Long groupId, Long senderId, Long recipientId, String text, Boolean seen) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.text = text;
        this.seen = seen;
    }

    // getters & setters
    public Long getMessageId() { return messageId; }
    public Long getGroupId() { return groupId; }
    public Long getSenderId() { return senderId; }
    public Long getRecipientId() { return recipientId; }
    public String getText() { return text; }
    public Boolean getSeen() { return seen; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setMessageId(Long messageId) { this.messageId = messageId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setSender(Long senderId) { this.senderId = senderId; }
    public void setRecipient(Long recipientId) { this.recipientId = recipientId; }
    public void setText(String text) { this.text = text; }
    public void setSeen(Boolean seen) { this.seen = seen; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
