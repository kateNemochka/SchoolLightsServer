package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.types.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);
    Room findByMicrocontroller(Microcontroller microcontroller);
    List<Room> findAllByFloor(Integer floor);
}
