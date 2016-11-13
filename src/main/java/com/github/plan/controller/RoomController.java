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
        roomRepository.save(room);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/room/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeRoom(@RequestBody Long roomId) {
        roomRepository.delete(roomId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
