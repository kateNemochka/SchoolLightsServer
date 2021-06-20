package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAll();
    List<Zone> getZonesByRoom(Room room);
    Zone getZoneById(Long id);
    Zone createOrUpdate(Zone zone);
    void deleteZoneById(Long id);
    Room addZoneToRoom(Zone zone, Room room);
    Zone createDefaultZone();
}
