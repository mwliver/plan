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
    GroupRepository groupRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean addEvent(Event event, Group group, Room room, User user) {
        List<Event> plannedOrOngoing = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo());

        if (!CollectionUtils.isEmpty(plannedOrOngoing)) {
            return false;
        }

        event = eventRepository.save(event);

        group.getEvents().add(event);
        room.getEvents().add(event);
        user.getEvents().add(event);

        groupRepository.save(group);
        roomRepository.save(room);
        userRepository.save(user);

        return true;
    }
}
