package com.github.plan.service;

import com.github.plan.persistence.client.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean addEvent(Event event, Team team, Room room, User user) {
        List<Event> plannedOrOngoing = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo());

        if (!CollectionUtils.isEmpty(plannedOrOngoing)) {
            return false;
        }

        team = teamRepository.save(team);
        room = roomRepository.save(room);
        user = userRepository.save(user);

        event.setTeam(team);
        event.setRoom(room);
        event.setUser(user);
        eventRepository.save(event);

        return true;
    }
}
