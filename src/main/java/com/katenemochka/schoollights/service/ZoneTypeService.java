package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.types.ZoneType;
import java.util.List;

public interface ZoneTypeService {
    List<ZoneType> getAll();
    ZoneType getZoneTypeById(Long id);
    ZoneType createOrUpdate(ZoneType zoneType);
    void deleteZoneTypeById(Long id);
}
