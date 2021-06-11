package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MicrocontrollerRepository extends JpaRepository<Microcontroller, Long> {
    Optional<Microcontroller> findByRoom(Room room);
    Optional<Microcontroller> findByMacAddress(String macAddress);
    Optional<Microcontroller> findByMacAddressAndRoom(String macAddress, Room room);
    List<Microcontroller> findAllByModeUpdate(Date modeUpdate);
    List<Microcontroller> findAllByRoomIsNull();
}