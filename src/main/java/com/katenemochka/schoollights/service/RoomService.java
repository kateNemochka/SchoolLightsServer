package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    Room getRoomById(Long id);
    Room createOrUpdate(Room room);
    void deleteRoomById(Long id);
}
