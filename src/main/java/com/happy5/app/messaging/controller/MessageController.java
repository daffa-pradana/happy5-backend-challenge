package com.happy5.app.messaging.controller;

import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.model.User;
import com.happy5.app.messaging.model.response.SendMessageResponse;
import com.happy5.app.messaging.service.MessageService;
import com.happy5.app.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    private UserService userService;
    private MessageService messageService;

    // inject
    @Autowired
    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    // Send message
    @PostMapping("/send/{recipient-id}")
    public ResponseEntity<SendMessageResponse> sendMessage(
            @PathVariable(value = "recipient-id") Long recipientId,
            @RequestHeader("sender-id") Long senderId,
            @RequestBody String text
    ) {
        User recipient = userService.findUserService(recipientId);
        MessageGroup currentGroup = messageService.addMessageGroupService(senderId, recipientId);
        Message createdMessage = messageService.addMessageService(senderId, recipientId, currentGroup.getGroupId(), text);

        // generate response
        String successMsg = "Message has been sent to " + recipient.getFirstName() + "";
        SendMessageResponse response = new SendMessageResponse(successMsg, createdMessage);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
