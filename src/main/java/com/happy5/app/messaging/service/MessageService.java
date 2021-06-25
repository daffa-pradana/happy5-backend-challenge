package com.happy5.app.messaging.service;

import com.happy5.app.messaging.exception.GroupNotFoundException;
import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.repository.MessageGroupRepository;
import com.happy5.app.messaging.repository.MessageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // Get message group
    public MessageGroup findMessageGroupService(Long groupId) {
        return messageGroupRepository.findMessageGroupByGroupId(groupId).
                orElseThrow(() -> new GroupNotFoundException("Message group with id " + groupId + " doesn't exist"));
    }

    // Extract recipient
    public Long extractRecipientService(String members, Long senderId) {

        // regex
        String memberStr = members.replaceAll("[ ]", "");

        // string builder
        StringBuilder memberSB = new StringBuilder(memberStr);
        memberSB.deleteCharAt(0);
        memberSB.deleteCharAt(memberSB.length()-1);
        String processed = memberSB.toString();

        // remove sender
        List<String> memberList = new ArrayList<>(Arrays.asList(processed.split(",")));
        memberList.remove(senderId.toString());

        return Long.parseLong(memberList.get(0));
    }

    // Add message
    public Message addMessageService(Long senderId, Long recipientId, Long groupId, String text) {
        // timestamp
        LocalDateTime currentTime = LocalDateTime.now();

        // set message
        Message newMessage = new Message(groupId, senderId, recipientId, text, false, currentTime);

        // save new message
        return messageRepository.save(newMessage);
    }

}
