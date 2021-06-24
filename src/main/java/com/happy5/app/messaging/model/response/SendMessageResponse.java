package com.happy5.app.messaging.model.response;

import com.happy5.app.messaging.model.Message;

public class SendMessageResponse {

    // props
    private String responseMessage;
    private Message createdMessage;

    // constructor - default
    public SendMessageResponse() {}

    // constructor - param
    public SendMessageResponse(String responseMessage, Message createdMessage) {
        this.responseMessage = responseMessage;
        this.createdMessage = createdMessage;
    }

    // getters & setters
    public String getResponseMessage() { return responseMessage; }
    public Message getCreatedMessage() { return createdMessage; }
    public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }
    public void setCreatedMessage(Message createdMessage) { this.createdMessage = createdMessage; }
}
