package com.happy5.app.messaging.service;

import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.repository.MessageGroupRepository;
import com.happy5.app.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // Add message group
    public MessageGroup addMessageGroupService(Long senderId, Long recipientId) {
        // sender & recipient validation
        if (senderId.equals(recipientId)) throw new IllegalArgumentException("Sender and Recipient cannot be the same!");

        // generate member
        List<Long> newMembers = Arrays.asList(senderId, recipientId);
        Collections.sort(newMembers);

        // check if existed
        boolean existed = messageGroupRepository.findMessageGroupByMembers(newMembers.toString()).isPresent();
        if (existed) throw new IllegalArgumentException("Message Group already existed, try using 'Reply' endpoint");

        // set members
        MessageGroup newGroup = new MessageGroup(newMembers.toString());

        // save new group
        return messageGroupRepository.save(newGroup);
    }

    // add message
    public Message addMessageService(Long senderId, Long recipientId, Long groupId, String text) {
        // timestamp
        LocalDateTime currentTime = LocalDateTime.now();

        // set message
        Message newMessage = new Message(groupId, senderId, recipientId, text, false, currentTime);

        // save new message
        return messageRepository.save(newMessage);
    }

}
