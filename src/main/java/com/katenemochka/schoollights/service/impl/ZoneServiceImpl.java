package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ZoneRepository;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.service.ZoneService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    ZoneRepository zoneRepository;

    @Override
    public List<Zone> getAll() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Zone createOrUpdate(Zone zone) {
        if (zone.getId() != null) {

            Optional<Zone> zoneOptional = zoneRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Zone newZone = zoneOptional.get();
                newZone.setName(zone.getName());
                return zoneRepository.save(newZone);
            }
        }
        return zoneRepository.save(zone);
    }

    @Override
    public void deleteZoneById(Long id) {
        Optional<Zone> zone = zoneRepository.findById(id);

        if (zone.isPresent()) {
            zoneRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone with given id");
        }
    }
}
