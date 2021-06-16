package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ZoneTypeRepository;
import com.katenemochka.schoollights.domain.ZoneType;
import com.katenemochka.schoollights.service.ZoneTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ZoneTypeServiceImpl implements ZoneTypeService {
    @Autowired
    ZoneTypeRepository zoneTypeRepository;

    @Override
    public List<ZoneType> getAll() {
        List<ZoneType> zones = zoneTypeRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public ZoneType getZoneTypeById(Long id) {
        return zoneTypeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(("Zone type with id=" + id + " doesn't exist")));
    }

    @Override
    public ZoneType createOrUpdate(ZoneType zone) {
        if (zone.getId() != null) {
            Optional<ZoneType> zoneOptional = zoneTypeRepository.findById(zone.getId());
            if (zoneOptional.isPresent()) {
                return zoneTypeRepository.save(zone);
            }
        }
        return zoneTypeRepository.save(zone);
    }

    @Override
    public void deleteZoneTypeById(Long id) {
        Optional<ZoneType> zoneType = zoneTypeRepository.findById(id);

        if (zoneType.isPresent()) {
            zoneTypeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can`t delete zone type with id=" + id + " because it doesn't exist");
        }
    }
}
