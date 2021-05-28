package com.katenemochka.schoollights.dao.jpa;

import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByFloor(int floor);
    List<Room> findRoomsByMode(Mode mode);

}
