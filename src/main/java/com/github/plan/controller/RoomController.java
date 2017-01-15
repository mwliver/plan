package com.github.plan.controller;

import com.github.plan.persistence.client.dao.Room;
import com.github.plan.persistence.client.dao.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class RoomController {
    @Inject
    private RoomRepository roomRepository;

    @RequestMapping(value = "/room/list", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @RequestMapping(value = "/room/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveRoom(@RequestBody Room room) {
        if (roomRepository.countByBuildingAndNumber(room.getBuilding(), room.getNumber()) == 0
                || roomRepository.countByBuildingAndNumber(room.getBuilding(), room.getNumber()) == 1
                && roomRepository.findByBuildingAndNumber(room.getBuilding(), room.getNumber()).getId() == room.getId()) {
            roomRepository.save(room);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/room/list/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveRooms(@RequestBody List<Room> rooms) {
        if (rooms.stream()
                .allMatch(room -> roomRepository.countByBuildingAndNumber(room.getBuilding(), room.getNumber()) == 0
                        || roomRepository.countByBuildingAndNumber(room.getBuilding(), room.getNumber()) == 1
                        && roomRepository.findByBuildingAndNumber(room.getBuilding(), room.getNumber()).getId() == room.getId())) {
            roomRepository.save(rooms);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/room/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeRoom(@RequestBody Long roomId) {
        roomRepository.delete(roomId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
