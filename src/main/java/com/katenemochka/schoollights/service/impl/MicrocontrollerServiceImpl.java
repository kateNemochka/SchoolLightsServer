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
        List<Microcontroller> microcontrollers = microcontrollerRepository.findAll();
        return microcontrollers.isEmpty() ? new ArrayList<>() : microcontrollers;
    }

    @Override
    public Microcontroller getMicrocontrollerById(Long id) {
        return microcontrollerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No microcontroller /w id " + id)));
    }

    @Override
    public Microcontroller createOrUpdate(Microcontroller microcontroller) {
        if (microcontroller.getId() != null) {

            Optional<Microcontroller> microcontrollerOptional = microcontrollerRepository.findById(microcontroller.getId());

            if (microcontrollerOptional.isPresent()) {
                Microcontroller newMicrocontroller = microcontrollerOptional.get();
                //TODO
                //newMicrocontroller.setName(microcontroller.getName());
                return microcontrollerRepository.save(newMicrocontroller);
            }
        }
        return microcontrollerRepository.save(microcontroller);
    }

    @Override
    public void deleteMicrocontrollerById(Long id) {
        Optional<Microcontroller> role = microcontrollerRepository.findById(id);

        if (role.isPresent()) {
            microcontrollerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no microcontroller type with given id");
        }
    }
}
