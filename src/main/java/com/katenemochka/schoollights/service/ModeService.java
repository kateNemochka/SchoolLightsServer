package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.types.Mode;

import java.util.List;

public interface ModeService {
    List<Mode> getAll();
    Mode getModeById(Long id);
    Mode getModeByName(String name);
    Mode createOrUpdate(Mode mode);
    void deleteModeById(Long id);
    void loadDefaultData();
}
