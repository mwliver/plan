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

        List<Event> plannedOrOngoing = eventRepository.findPlannedOrOngoing(event.getTimeFrom(), event.getTimeTo());

        if (plannedOrOngoing.stream().filter(n -> n.getUser().getId().equals(user.getId())).count() > 0) {
            return false;
        }

        if (plannedOrOngoing.stream().filter(n -> n.getRoom().getId().equals(room.getId())).count() > 0) {
            return false;
        }

        if (plannedOrOngoing.stream().filter(n -> n.getTeam().getId().equals(team.getId())).count() > 0) {
            return false;
        }

        eventRepository.save(event);

        return true;
    }
}
