package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ZoneTypeRepository;
import com.katenemochka.schoollights.domain.types.ZoneType;
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
        return zoneTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public ZoneType createOrUpdate(ZoneType zone) {
        if (zone.getId() != null) {

            Optional<ZoneType> zoneOptional = zoneTypeRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                ZoneType newZoneType = zoneOptional.get();
                newZoneType.setName(zone.getName());
                return zoneTypeRepository.save(newZoneType);
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
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
