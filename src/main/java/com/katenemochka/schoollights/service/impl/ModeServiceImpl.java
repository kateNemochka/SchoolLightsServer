package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.service.ModeService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ModeServiceImpl implements ModeService {
    @Override
    public List<Mode> getAll() {
        return null;
    }

    @Override
    public Mode getModeById(Long id) {
        return null;
    }

    @Override
    public Mode createOrUpdate(Mode mode) {
        return null;
    }

    @Override
    public void deleteModeById(Long id) {

    }
}
