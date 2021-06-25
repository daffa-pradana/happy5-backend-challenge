package com.happy5.app.messaging.service;

import com.happy5.app.messaging.exception.GroupNotFoundException;
import com.happy5.app.messaging.exception.UserNotAuthorizedException;
import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.model.response.ConversationResponse;
import com.happy5.app.messaging.repository.MessageGroupRepository;
import com.happy5.app.messaging.repository.MessageRepository;
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

    // List message group
    public List<MessageGroup> listMessageGroupService(Long id) {
        String userId = id.toString();
        return messageGroupRepository.findMessageGroupByMembersContains(userId);
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

    // Get conversations
    public List<ConversationResponse> getConversationService(List<MessageGroup> messageGroups, Long userId) {
        List<ConversationResponse> conversationResponses = new ArrayList<>();

        for (MessageGroup group: messageGroups) {
            // list message
            List<Message> messageList = listMessageService(group.getGroupId(), userId);

            // count unread message
            int unreadMessage = 0;
            for (Message message: messageList) {
                if (!message.getSeen() && message.getRecipientId().equals(userId)) unreadMessage++;
            }

            // get last message
            Message lastMessage = messageList.get(messageList.size()-1);

            // generate response
            ConversationResponse res = new ConversationResponse(group, lastMessage, unreadMessage);

            // add to list
            conversationResponses.add(res);
        }

        return conversationResponses;
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

    // List message
    public List<Message> listMessageService(Long groupId, Long userId) {
        // find group
        MessageGroup messageGroup = findMessageGroupService(groupId);

        // authorize user
        String msg = "Your'e not authorized to access this conversation";
        if (!messageGroup.getMembers().contains(userId.toString())) throw new UserNotAuthorizedException(msg);

        // find messages
        return messageRepository.findMessageByGroupId(groupId);
    }

    // Read message WIP


}
