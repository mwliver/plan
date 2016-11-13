package com.github.plan.service;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class EventServiceTest extends Assert {

    @Autowired
    private EventService eventService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void addEvent() throws Exception {
        // Given
        Event event = new Event();
        event.setTimeFrom(Calendar.getInstance());
        event.setTimeTo(Calendar.getInstance());

        Team team = new Team();
        team.setName("team");

        Room room = new Room();
        room.setBuilding("33");
        room.setNumber("34");

        User user = new User();
        user.setName("michal");

        // When
        Boolean result = eventService.addEvent(event, team, room, user);

        // Then
        assertTrue(result);
    }
}
