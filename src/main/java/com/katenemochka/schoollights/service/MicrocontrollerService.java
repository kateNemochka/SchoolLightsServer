package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Microcontroller;

import java.util.List;

public interface MicrocontrollerService {
    List<Microcontroller> getAll();
    Microcontroller getMicrocontrollerById(Long id);
    Microcontroller createOrUpdate(Microcontroller microcontroller);
    void deleteMicrocontrollerById(Long id);
}
