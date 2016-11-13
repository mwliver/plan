package com.github.plan.controller;


import com.github.plan.persistence.client.dao.Event;
import com.github.plan.persistence.client.dao.Group;
import com.github.plan.persistence.client.dao.Room;
import com.github.plan.persistence.client.dao.User;
import com.github.plan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public ResponseEntity<String> addEvent(@RequestBody Event event,
                                           @RequestBody Group group,
                                           @RequestBody Room room,
                                           @RequestBody User user) {
        Boolean result = eventService.addEvent(event, group, room, user);
        if (Boolean.TRUE.equals(result)) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Zajęte", HttpStatus.OK);
        }
    }
}
