package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.service.ModeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ModeServiceImpl implements ModeService {
    @Autowired
    ModeRepository modeRepository;

    @Override
    public List<Mode> getAll() {
        List<Mode> zones = modeRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Mode getModeById(Long id) {
        return modeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Mode createOrUpdate(Mode zone) {
        if (zone.getId() != null) {

            Optional<Mode> zoneOptional = modeRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Mode newMode = zoneOptional.get();
                //TODO
                //newMode.setName(zone.getName());
                return modeRepository.save(newMode);
            }
        }
        return modeRepository.save(zone);
    }

    @Override
    public void deleteModeById(Long id) {
        Optional<Mode> role = modeRepository.findById(id);

        if (role.isPresent()) {
            modeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
