package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.types.ZoneType;
import com.katenemochka.schoollights.service.ZoneTypeService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ZoneTypeServiceImpl implements ZoneTypeService {
    @Override
    public List<ZoneType> getAll() {
        return null;
    }

    @Override
    public ZoneType getZoneTypeById(Long id) {
        return null;
    }

    @Override
    public ZoneType createOrUpdate(ZoneType zoneType) {
        return null;
    }

    @Override
    public void deleteZoneTypeById(Long id) {

    }
}
