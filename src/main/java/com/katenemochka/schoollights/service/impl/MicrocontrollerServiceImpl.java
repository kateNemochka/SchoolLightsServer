package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.MicrocontrollerRepository;
import com.katenemochka.schoollights.dao.RoomRepository;
import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.service.MicrocontrollerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Data
public class MicrocontrollerServiceImpl implements MicrocontrollerService {
    @Autowired
    MicrocontrollerRepository microcontrollerRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Microcontroller> getAll() {
        List<Microcontroller> microcontrollers = microcontrollerRepository.findAll();
        return microcontrollers.isEmpty() ? new ArrayList<>() : microcontrollers;
    }

    @Override
    public List<Microcontroller> getFreeMicrocontrollers() {
        List<Microcontroller> microcontrollers = microcontrollerRepository.findAllByRoomIsNull();
        return microcontrollers.isEmpty() ? new ArrayList<>() : microcontrollers;
    }

    @Override
    public Microcontroller getMicrocontrollerById(Long id) {
        return microcontrollerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(("No microcontroller with id " + id)));
    }

    @Override
    public Microcontroller createOrUpdate(Microcontroller micro) {
        if (micro.getId() != null) {
            Optional<Microcontroller> microEntity =
                    microcontrollerRepository.findById(micro.getId());
            if (microEntity.isPresent()) {
                Microcontroller newMicro = microEntity.get();
                newMicro.setMacAddress(micro.getMacAddress().toUpperCase());
                newMicro.setIpAddress(micro.getIpAddress());
                newMicro.setMqttUsername(micro.getMqttUsername());
                newMicro.setSensorsUpdateTimeout(micro.getSensorsUpdateTimeout());
                newMicro.setStatusUpdateTimeout(micro.getStatusUpdateTimeout());
                return microcontrollerRepository.save(newMicro);
            }
        }
        return microcontrollerRepository.save(micro);
    }

    @Override
    public void deleteMicrocontrollerById(Long id) {
        Optional<Microcontroller> micro = microcontrollerRepository.findById(id);
        if (micro.isPresent()) {
            if (micro.get().getRoom() != null) {
                Room room = roomRepository.findByMicrocontroller(micro.get());
                room.setMicrocontroller(null);
                roomRepository.save(room);
            }
            microcontrollerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no microcontroller type with given id");
        }
    }

    @Override
    public void addMicrocontrollerToRoom(Microcontroller micro, Room room) {
        Room roomEntity = roomRepository.findById(room.getId()).orElseThrow();
        if (microcontrollerRepository.findByMacAddressAndRoom(micro.getMacAddress(), roomEntity).isEmpty()) {
            micro.setRoom(roomEntity);
            microcontrollerRepository.save(micro);
            roomEntity.setMicrocontroller(micro);
            roomRepository.save(roomEntity);
        }
    }

    @Override
    public void removeMicrocontrollerFromRoom(Microcontroller micro, Room room) {
        Room roomEntity = roomRepository.findById(room.getId()).orElseThrow();
        if (microcontrollerRepository.findByMacAddressAndRoom(micro.getMacAddress(), roomEntity).isPresent()) {
            roomEntity.setMicrocontroller(null);
            roomRepository.save(roomEntity);
            micro.setRoom(null);
            microcontrollerRepository.save(micro);
        }
    }
}
