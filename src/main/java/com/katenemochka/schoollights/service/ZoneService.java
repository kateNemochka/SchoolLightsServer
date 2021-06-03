package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAll();
    Zone getZoneById(Long id);
    Zone createOrUpdate(Zone zone);
    void deleteZoneById(Long id);
}
