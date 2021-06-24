package com.happy5.app.messaging.service;

import com.happy5.app.messaging.repository.MessageGroupRepository;
import com.happy5.app.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageGroupRepository messageGroupRepository;

    // inject
    @Autowired
    public MessageService(MessageRepository messageRepository, MessageGroupRepository messageGroupRepository) {
        this.messageRepository = messageRepository;
        this.messageGroupRepository = messageGroupRepository;
    }

    // add message group

    // add message

}
