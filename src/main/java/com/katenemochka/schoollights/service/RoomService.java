package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    List<Room> getRoomsByFloor(int floor);
    Room getRoomById(Long id);
    Room createOrUpdate(Room room);
    void deleteRoomById(Long id);
    void updateColorTemperature(Room room, int colorTemperature);
    void updateColorTemperature(Long roomId, int colorTemperature);
}
