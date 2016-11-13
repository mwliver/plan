package com.github.plan.service;

import com.github.plan.persistence.client.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean addEvent(Event event, Team team, Room room, User user) {
        List<Event> plannedOrOngoingForUser = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo(), user);

        if (!CollectionUtils.isEmpty(plannedOrOngoingForUser)) {
            return false;
        }

        List<Event> plannedOrOngoingForRoom = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo(), room);

        if (!CollectionUtils.isEmpty(plannedOrOngoingForRoom)) {
            return false;
        }

        List<Event> plannedOrOngoingForTeam = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo(), team);

        if (!CollectionUtils.isEmpty(plannedOrOngoingForTeam)) {
            return false;
        }

        if (team.getId() != null) {
            team = teamRepository.findOne(team.getId());
        } else {
            team = teamRepository.save(team);
        }
        if (room.getId() != null) {
            room = roomRepository.findOne(room.getId());
        } else {
            room = roomRepository.save(room);
        }
        if (user.getId() != null) {
            user = userRepository.findOne(user.getId());
        } else {
            user = userRepository.save(user);
        }

        event.setTeam(team);
        event.setRoom(room);
        event.setUser(user);
        eventRepository.save(event);

        return true;
    }
}
