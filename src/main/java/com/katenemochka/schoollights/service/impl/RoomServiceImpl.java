package com.katenemochka.schoollights.service.impl;


import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.service.RoomService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RoomServiceImpl implements RoomService {
    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public Room getRoomById(Long id) {
        return null;
    }

    @Override
    public Room createOrUpdate(Room room) {
        return null;
    }

    @Override
    public void deleteRoomById(Long id) {

    }
}
