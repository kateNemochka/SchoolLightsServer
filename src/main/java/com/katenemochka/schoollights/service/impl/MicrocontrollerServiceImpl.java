package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.MicrocontrollerRepository;
import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.service.MicrocontrollerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class MicrocontrollerServiceImpl implements MicrocontrollerService {
    @Autowired
    MicrocontrollerRepository microcontrollerRepository;

    @Override
    public List<Microcontroller> getAll() {
        List<Microcontroller> zones = microcontrollerRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Microcontroller getMicrocontrollerById(Long id) {
        return microcontrollerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Microcontroller createOrUpdate(Microcontroller zone) {
        if (zone.getId() != null) {

            Optional<Microcontroller> zoneOptional = microcontrollerRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Microcontroller newMicrocontroller = zoneOptional.get();
                //TODO
                //newMicrocontroller.setName(zone.getName());
                return microcontrollerRepository.save(newMicrocontroller);
            }
        }
        return microcontrollerRepository.save(zone);
    }

    @Override
    public void deleteMicrocontrollerById(Long id) {
        Optional<Microcontroller> role = microcontrollerRepository.findById(id);

        if (role.isPresent()) {
            microcontrollerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
