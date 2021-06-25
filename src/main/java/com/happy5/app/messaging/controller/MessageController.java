package com.happy5.app.messaging.controller;

import com.happy5.app.messaging.model.Message;
import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.model.User;
import com.happy5.app.messaging.model.response.MessageResponse;
import com.happy5.app.messaging.service.MessageService;
import com.happy5.app.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public ResponseEntity<MessageResponse> sendMessage(
            @PathVariable(value = "recipient-id") Long recipientId,
            @RequestHeader("sender-id") Long senderId,
            @RequestBody String text
    ) {
        // find recipient
        User recipient = userService.findUserService(recipientId);

        // find message group
        MessageGroup currentGroup = messageService.addMessageGroupService(senderId, recipientId);

        // create message
        Message createdMessage = messageService.addMessageService(senderId, recipientId, currentGroup.getGroupId(), text);

        // generate response
        String successMessage = "Message has been sent to " + recipient.getFirstName() + "";
        MessageResponse response = new MessageResponse(successMessage, createdMessage);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // Reply message
    @PostMapping("/reply/{group-id}")
    public ResponseEntity<MessageResponse> replyMessage(
            @PathVariable(value = "group-id") Long groupId,
            @RequestHeader("sender-id") Long senderId,
            @RequestBody String text
    ) {
        // find message group
        MessageGroup group = messageService.findMessageGroupService(groupId);

        // extract recipient id
        Long recipientId = messageService.extractRecipientService(group.getMembers(), senderId);

        // get recipient
        User recipient = userService.findUserService(recipientId);

        // create message
        Message createdMessage = messageService.addMessageService(senderId, recipientId, groupId, text);

        // set seen to true later on..

        // generate response
        String successMessage = "The message have been replied to " + recipient.getFirstName() + " successfully";
        MessageResponse response = new MessageResponse(successMessage, createdMessage);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // List conversation
    @GetMapping("/conversations")
    public ResponseEntity<?> listConversations(
            @RequestHeader("user-id") Long id
    ) {
        // list message group
        List<MessageGroup> messageGroups = messageService.listMessageGroupService(id);

        return new ResponseEntity<>(messageGroups,HttpStatus.OK);
    }

    // List message WIP
}
