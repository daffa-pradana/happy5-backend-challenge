package com.happy5.app.messaging.model.response;

import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;

import java.util.List;

public class ConversationResponse {

    // props
    private MessageGroup messageGroups;
    private Message lastMessage;
    private int unreadMessage;

    // constructor - default
    public ConversationResponse() {}

    // constructor - param
    public ConversationResponse(MessageGroup messageGroups, Message lastMessage, int unreadMessage) {
        this.messageGroups = messageGroups;
        this.lastMessage = lastMessage;
        this.unreadMessage = unreadMessage;
    }

    // getters & setters
    public MessageGroup getMessageGroups() { return messageGroups; }
    public Message getLastMessage() { return lastMessage; }
    public int getUnreadMessage() { return unreadMessage; }
    public void setMessageGroups(MessageGroup messageGroups) { this.messageGroups = messageGroups; }
    public void setLastMessage(Message lastMessage) { this.lastMessage = lastMessage; }
    public void setUnreadMessage(int unreadMessage) { this.unreadMessage = unreadMessage; }
}
