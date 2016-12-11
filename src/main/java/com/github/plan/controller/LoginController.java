package com.github.plan.controller;

import com.github.plan.persistence.client.dao.User;
import com.github.plan.persistence.client.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByLogin(username);
        if (user != null && user.getPassword().equals(password)) {
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        }
    }
}
