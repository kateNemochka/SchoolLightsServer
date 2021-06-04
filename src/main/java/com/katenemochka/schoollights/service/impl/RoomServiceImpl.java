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
        List<Room> zones = roomRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Room createOrUpdate(Room zone) {
        if (zone.getId() != null) {

            Optional<Room> zoneOptional = roomRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Room newRoom = zoneOptional.get();
                //TODO
                //newRoom.setName(zone.getName());
                return roomRepository.save(newRoom);
            }
        }
        return roomRepository.save(zone);
    }

    @Override
    public void deleteRoomById(Long id) {
        Optional<Room> role = roomRepository.findById(id);

        if (role.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
