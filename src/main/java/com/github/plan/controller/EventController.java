package com.github.plan.controller;


import com.github.plan.persistence.client.dao.*;
import com.github.plan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public ResponseEntity<String> addEvent(@RequestBody Event event,
                                           @RequestBody Team team,
                                           @RequestBody Room room,
                                           @RequestBody User user) {
        Boolean result = eventService.addEvent(event, team, room, user);
        if (Boolean.TRUE.equals(result)) {
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/event/list", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/event/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/event/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeEvent(@RequestBody Long eventId) {
        eventRepository.delete(eventId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
