package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public interface MicrocontrollerRepository extends JpaRepository<Microcontroller, Long> {
    Microcontroller findByRoom(Room room);
    Microcontroller findByMacAddress(String macAddress);
    Collection<Microcontroller> findAllByModeUpdate(Date modeUpdate);
}