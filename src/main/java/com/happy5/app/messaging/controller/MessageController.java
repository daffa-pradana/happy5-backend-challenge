package com.happy5.app.messaging.controller;

import com.happy5.app.messaging.service.MessageService;
import com.happy5.app.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // Send a message ..

}
