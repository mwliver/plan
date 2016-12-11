package com.github.plan.controller;

import com.github.plan.persistence.client.dao.User;
import com.github.plan.persistence.client.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        if (userRepository.countByName(user.getName()) == 0) {
            userRepository.save(user);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/user/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeUser(@RequestBody Long userId) {
        userRepository.delete(userId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
