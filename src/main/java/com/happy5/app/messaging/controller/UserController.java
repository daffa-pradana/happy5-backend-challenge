package com.happy5.app.messaging.controller;

import com.happy5.app.messaging.model.User;
import com.happy5.app.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    // List users
    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.findAllUsersService();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        User currentUser = userService.findUserService(id);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    // Add user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User currentUser = userService.addUserService(user);
        return new ResponseEntity<>(currentUser, HttpStatus.CREATED);
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
