package com.github.plan.controller;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.Room;
import com.github.plan.persistence.client.dao.RoomRepository;
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
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class RoomControllerTest extends Assert {

    @Autowired
    private RoomController roomController;

    @Autowired
    private RoomRepository roomRepository;

    private RoomController roomControllerMock;

    @Before
    public void setUp() {
        roomControllerMock = mock(roomController.getClass());
    }

    @Test
    public void roomTestMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");

        // When
        when(roomControllerMock.saveRoom(room)).thenReturn(new ResponseEntity<String>(HttpStatus.ACCEPTED));
        ResponseEntity<String> response = roomControllerMock.saveRoom(room);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void roomTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");

        // When
        ResponseEntity<String> response = roomController.saveRoom(room);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void removeTestMock() throws Exception {
        // When
        when(roomControllerMock.removeRoom(any())).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
        ResponseEntity<String> response = roomControllerMock.removeRoom(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");
        room = roomRepository.save(room);

        // When
        ResponseEntity<String> response = roomController.removeRoom(room.getId());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listTestMock() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");

        Room room2 = new Room();
        room2.setBuilding("10");
        room2.setNumber("10");

        List<Room> expected = Arrays.asList(room, room2);

        // When
        when(roomControllerMock.getRooms()).thenReturn(expected);
        List<Room> actual = roomControllerMock.getRooms();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestImpl() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");
        room = roomRepository.save(room);

        Room room2 = new Room();
        room2.setBuilding("10");
        room2.setNumber("10");
        room2 = roomRepository.save(room2);

        List<Room> expected = Arrays.asList(room, room2);

        // When
        List<Room> actual = roomController.getRooms();

        // Then
        assertEquals(expected, actual);
    }
}

