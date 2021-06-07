package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.service.ModeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Data
public class ModeServiceImpl implements ModeService {
    @Autowired
    ModeRepository modeRepository;

    @Override
    public List<Mode> getAll() {
        List<Mode> modes = modeRepository.findAll();
        return modes.isEmpty() ? new ArrayList<>() : modes;
    }

    @Override
    public Mode getModeById(Long id) {
        return modeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("No mode /w id " + id)));
    }

    @Override
    public Mode getModeByName(String name) {
        return modeRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(("No mode with name " + name)));
    }

    @Override
    public Mode createOrUpdate(Mode mode) {
        if (mode.getId() != null) {

            Optional<Mode> modeOptional = modeRepository.findById(mode.getId());

            if (modeOptional.isPresent()) {
                Mode newMode = modeOptional.get();
                return modeRepository.save(newMode);
            }
        }
        return modeRepository.save(mode);
    }

    @Override
    public void deleteModeById(Long id) {
        Optional<Mode> role = modeRepository.findById(id);

        if (role.isPresent()) {
            modeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no mode type with given id");
        }
    }

    public void loadDefaultData() {
        List<Mode> defaultModes = new LinkedList<>();
        defaultModes.add(new Mode("INIT", "Ініціалізація"));
        defaultModes.add(new Mode("AUTO", "Автоматичний"));
        defaultModes.add(new Mode("ADAPTIVE", "Адаптивна освітленість"));
        defaultModes.add(new Mode("FULL_POWER", "Повна потужність"));
        defaultModes.add(new Mode("MANUAL", "Ручний режим"));
        defaultModes.add(new Mode("OFF", "Вимкнути"));
        defaultModes.add(new Mode("PRESENTATION", "Режим презентації"));

        for (Mode mode : defaultModes) {
            try {
                Mode existingMode = this.getModeByName(mode.getName());
                mode.setId(existingMode.getId());
            } catch (EntityNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            this.createOrUpdate(mode);
        }
    }
}
