package com.happy5.app.messaging.service;

import com.happy5.app.messaging.exception.UserNotFoundException;
import com.happy5.app.messaging.model.User;
import com.happy5.app.messaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // add user
    public User addUserService(User user) {
        return userRepository.save(user);
    }

    // find user
    public User findUserService(Long id) {
        return userRepository.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("User with id" + id + " is not registered"));
    }

    // find all users
    public List<User> findAllUsersService() {
        return userRepository.findAll();
    }

    // delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
