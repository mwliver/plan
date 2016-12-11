package com.github.plan.controller;


import com.github.plan.persistence.client.dao.*;
import com.github.plan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/event/list/user/{userId}", method = RequestMethod.GET)
    public List<Event> getEventsByUserId(@PathVariable("userId") Long userId) {
        List<Event> events = eventRepository.findAll();

        return events.stream().filter(n -> n.getUser().getId() == userId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/event/list/team/{teamId}", method = RequestMethod.GET)
    public List<Event> getEventsByTeamId(@PathVariable("teamId") Long teamId) {
        List<Event> events = eventRepository.findAll();

        return events.stream().filter(n -> n.getTeam().getId() == teamId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/event/list/room/{roomId}", method = RequestMethod.GET)
    public List<Event> getEventsByRoomId(@PathVariable("roomId") Long roomId) {
        List<Event> events = eventRepository.findAll();

        return events.stream().filter(n -> n.getRoom().getId() == roomId).collect(Collectors.toList());
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
