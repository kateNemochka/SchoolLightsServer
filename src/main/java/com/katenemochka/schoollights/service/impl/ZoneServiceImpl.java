package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.service.ZoneService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ZoneServiceImpl implements ZoneService {
    @Override
    public List<Zone> getAll() {
        return null;
    }

    @Override
    public Zone getZoneById(Long id) {
        return null;
    }

    @Override
    public Zone createOrUpdate(Zone zone) {
        return null;
    }

    @Override
    public void deleteZoneById(Long id) {

    }
}
