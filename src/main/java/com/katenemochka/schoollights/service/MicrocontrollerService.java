package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;

import java.util.List;

public interface MicrocontrollerService {
    List<Microcontroller> getAll();
    List<Microcontroller> getFreeMicrocontrollers();
    Microcontroller getMicrocontrollerById(Long id);
    Microcontroller createOrUpdate(Microcontroller microcontroller);
    void deleteMicrocontrollerById(Long id);
    void addMicrocontrollerToRoom(Microcontroller microcontroller, Room room);
    void removeMicrocontrollerFromRoom(Microcontroller microcontroller, Room room);
}
