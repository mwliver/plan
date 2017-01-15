package com.github.plan.controller;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class EventControllerTest extends Assert {

    @Autowired
    private EventController eventController;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    private EventController eventControllerMock;

    @Before
    public void setUp() {
        eventControllerMock = mock(eventController.getClass());
    }

    @Test
    public void roomTestMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        // When
        when(eventControllerMock
                .saveEvent(event)).thenReturn(new ResponseEntity<String>(HttpStatus.ACCEPTED));
        ResponseEntity<String> response = eventControllerMock.saveEvent(event);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void roomTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        // When
        ResponseEntity<String> response = eventController.saveEvent(event);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void removeTestMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        // When
        when(eventControllerMock
                .removeEvent(event.getId())).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
        ResponseEntity<String> response = eventControllerMock.removeEvent(event.getId());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        // When
        ResponseEntity<String> response = eventController.removeEvent(event.getId());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listByRoomIdTestMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        when(eventControllerMock
                .getEventsByRoomId(room.getId(), 2017, 1)).thenReturn(expected);
        List<Event> actual = eventControllerMock.getEventsByRoomId(room.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listByRoomIdTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        List<Event> actual = eventController.getEventsByRoomId(room.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestByTeamIdMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);
        timeTo.set(2017, 1, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        when(eventControllerMock
                .getEventsByTeamId(team.getId(), 2017, 1)).thenReturn(expected);
        List<Event> actual = eventControllerMock.getEventsByTeamId(team.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestByTeamIdImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        List<Event> actual = eventController.getEventsByTeamId(team.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestByUserIdMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        userRepository.save(user);
        eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        when(eventControllerMock
                .getEventsByUserId(user.getId(), 2017, 1)).thenReturn(expected);
        List<Event> actual = eventControllerMock.getEventsByUserId(user.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestByUserIdImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("A");
        room.setNumber("1");

        Team team = new Team();
        team.setName("Gr 1");

        User user = new User();
        user.setName("John Doe");
        user.setLogin("john.doe");
        user.setPassword("123");

        Calendar timeFrom = Calendar.getInstance();
        timeFrom.set(2017, Calendar.JANUARY, 1, 10, 0);

        Calendar timeTo = Calendar.getInstance();
        timeTo.set(2017, Calendar.JANUARY, 1, 12, 0);

        Event event = new Event();
        event.setRoom(room);
        event.setTeam(team);
        event.setUser(user);
        event.setTimeFrom(Calendar.getInstance());

        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        roomRepository.save(room);
        teamRepository.save(team);
        user = userRepository.save(user);
        event = eventRepository.save(event);

        List<Event> expected = Arrays.asList(event);

        // When
        List<Event> actual = eventController.getEventsByUserId(user.getId(), 2017, 1);

        // Then
        assertEquals(expected, actual);
    }
}

