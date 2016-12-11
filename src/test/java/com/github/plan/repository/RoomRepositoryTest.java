package com.github.plan.repository;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.Room;
import com.github.plan.persistence.client.dao.RoomRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class RoomRepositoryTest extends Assert {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void countByBuildingAndNumberTest() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");
        roomRepository.save(room);

        // When
        int countByBuildingAndNumber = roomRepository.countByBuildingAndNumber("12", "12");

        // Then
        assertEquals(1, countByBuildingAndNumber);
    }

    @Test
    public void countByBuildingAndNumberTest2() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");
        roomRepository.save(room);

        // When
        int countByBuildingAndNumber = roomRepository.countByBuildingAndNumber("12", "13");

        // Then
        assertEquals(0, countByBuildingAndNumber);
    }

    @Test
    public void countByBuildingAndNumberTest3() throws Exception {
        // Given
        Room room = new Room();
        room.setBuilding("12");
        room.setNumber("12");
        roomRepository.save(room);

        Room roomSecond = new Room();
        roomSecond.setBuilding("13");
        roomSecond.setNumber("12");
        roomRepository.save(roomSecond);

        // When
        int countByBuildingAndNumber = roomRepository.countByBuildingAndNumber("12", "12");

        // Then
        assertEquals(1, countByBuildingAndNumber);
    }
}
