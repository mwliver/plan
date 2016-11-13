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

        event = eventRepository.save(event);

        team.getEvents().add(event);
        room.getEvents().add(event);
        user.getEvents().add(event);

        teamRepository.save(team);
        roomRepository.save(room);
        userRepository.save(user);

        return true;
    }
}
