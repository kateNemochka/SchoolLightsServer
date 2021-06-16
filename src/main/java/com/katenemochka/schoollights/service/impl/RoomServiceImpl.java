package com.katenemochka.schoollights.service.impl;


import com.katenemochka.schoollights.dao.RoomRepository;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.service.RoomService;
import com.katenemochka.schoollights.service.ZoneService;
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
    @Autowired
    ZoneService zoneService;

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }

    @Override
    public List<Room> getRoomsByFloor(int floor) {
        List<Room> rooms = roomRepository.findAllByFloor(floor);
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No room with id " + id)));
    }

    @Override
    public Room createOrUpdate(Room room) {
        if (room.getId() != null) {
            Optional<Room> roomOptional = roomRepository.findById(room.getId());
            if (roomOptional.isPresent()) {
                Room newRoom = roomOptional.get();
                newRoom.setName(room.getName());
                newRoom.setMqttName(room.getMqttName());
                newRoom.setPurpose(room.getPurpose());
                newRoom.setDescription(room.getDescription());
                newRoom.setColorTemperature(room.getColorTemperature());
                newRoom.setFloor(room.getFloor());
                newRoom.setInnerRoom(room.isInnerRoom());
                return roomRepository.save(newRoom);
            }
        }
        Room savedRoom = roomRepository.save(room);
        Zone defaultZone = zoneService.createDefaultZone();
        return zoneService.addZoneToRoom(defaultZone, savedRoom);
    }

    @Override
    public void deleteRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no room type with given id");
        }
    }

    @Override
    public void updateColorTemperature(Room room, int colorTemperature) {
        if (room.getId() != null) {
            Optional<Room> roomOptional = roomRepository.findById(room.getId());
            if (roomOptional.isPresent()) {
                Room newRoom = roomOptional.get();
                newRoom.setColorTemperature(room.getColorTemperature());
                roomRepository.save(newRoom);
            }
        }
    }

    @Override
    public void updateColorTemperature(Long roomId, int colorTemperature) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setColorTemperature(colorTemperature);
            roomRepository.save(room);
        } else {
            throw new EntityNotFoundException("There is no room type with given id");
        }
    }
}
