package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.types.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);
    Collection<Room> findAllByFloor(Integer floor);
}
