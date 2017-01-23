package com.github.plan.controller;


import com.github.plan.persistence.client.dao.*;
import com.github.plan.service.EventService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.IllegalFieldValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private class DateTimePair {
        DateTime startOfWeek;
        DateTime endOfWeek;

        DateTimePair(int numberOfWeek, int year) {
            startOfWeek = new DateTime(year, 1, 1, 0, 0, 0, 0)
                    .plusWeeks(numberOfWeek - 1)
                    .withDayOfWeek(DateTimeConstants.MONDAY);
            endOfWeek = new DateTime(year, 1, 1, 23, 59, 59, 999)
                    .plusWeeks(numberOfWeek - 1)
                    .withDayOfWeek(DateTimeConstants.SUNDAY);
        }

        public Calendar getStartOfWeek() {
            return startOfWeek.toGregorianCalendar();
        }

        public Calendar getEndOfWeek() {
            return endOfWeek.toGregorianCalendar();
        }
    }

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
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/event/list/user/{userId}/{year}/{numberOfWeek}", method = RequestMethod.GET)
    public List<Event> getEventsByUserId(@PathVariable("userId") Long userId,
                                         @PathVariable("year") Integer year,
                                         @PathVariable("numberOfWeek") Integer numberOfWeek) {
        DateTimePair dtp;

        try {
            dtp = new DateTimePair(numberOfWeek, year);
        } catch (IllegalFieldValueException illegalFieldValueException) {
            return new ArrayList<>();
        }

        List<Event> events = eventRepository.findPlannedOrOngoing(dtp.getStartOfWeek(), dtp.getEndOfWeek());

        return events.stream().filter(n -> n.getUser().getId() == userId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/event/list/team/{teamId}/{year}/{numberOfWeek}", method = RequestMethod.GET)
    public List<Event> getEventsByTeamId(@PathVariable("teamId") Long teamId,
                                         @PathVariable("year") Integer year,
                                         @PathVariable("numberOfWeek") Integer numberOfWeek) {
        DateTimePair dtp;

        try {
            dtp = new DateTimePair(numberOfWeek, year);
        } catch (IllegalFieldValueException illegalFieldValueException) {
            return new ArrayList<>();
        }

        List<Event> events = eventRepository.findPlannedOrOngoing(dtp.getStartOfWeek(), dtp.getEndOfWeek());

        return events.stream().filter(n -> n.getTeam().getId() == teamId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/event/list/room/{roomId}/{year}/{numberOfWeek}", method = RequestMethod.GET)
    public List<Event> getEventsByRoomId(@PathVariable("roomId") Long roomId,
                                         @PathVariable("year") Integer year,
                                         @PathVariable("numberOfWeek") Integer numberOfWeek) {
        DateTimePair dtp;

        try {
            dtp = new DateTimePair(numberOfWeek, year);
        } catch (IllegalFieldValueException illegalFieldValueException) {
            return new ArrayList<>();
        }

        List<Event> events = eventRepository.findPlannedOrOngoing(dtp.getStartOfWeek(), dtp.getEndOfWeek());

        return events.stream().filter(n -> n.getRoom().getId() == roomId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/event/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/event/list/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveEvents(@RequestBody List<Event> events) {
        eventRepository.save(events);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/event/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeEvent(@RequestParam Long eventId) {
        eventRepository.delete(eventId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
