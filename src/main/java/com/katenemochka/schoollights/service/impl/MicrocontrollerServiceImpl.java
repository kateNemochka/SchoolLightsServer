package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.service.MicrocontrollerService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MicrocontrollerServiceImpl implements MicrocontrollerService {
    @Override
    public List<Microcontroller> getAll() {
        return null;
    }

    @Override
    public Microcontroller getMicrocontrollerById(Long id) {
        return null;
    }

    @Override
    public Microcontroller createOrUpdate(Microcontroller microcontroller) {
        return null;
    }

    @Override
    public void deleteMicrocontrollerById(Long id) {

    }
}
