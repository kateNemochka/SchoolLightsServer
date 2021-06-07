package com.katenemochka.schoollights.service.impl;


import com.katenemochka.schoollights.dao.RoomRepository;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.service.RoomService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No room /w id " + id)));
    }

    @Override
    public Room createOrUpdate(Room room) {
        if (room.getId() != null) {

            Optional<Room> roomOptional = roomRepository.findById(room.getId());

            if (roomOptional.isPresent()) {
                Room newRoom = roomOptional.get();
                //TODO
                //newRoom.setName(room.getName());
                return roomRepository.save(newRoom);
            }
        }
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long id) {
        Optional<Room> role = roomRepository.findById(id);

        if (role.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no room type with given id");
        }
    }
}
