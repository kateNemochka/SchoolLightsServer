package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DeviceRepository;
import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.service.DeviceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public List<Device> getAll() {
        List<Device> zones = deviceRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Device createOrUpdate(Device zone) {
        if (zone.getId() != null) {

            Optional<Device> zoneOptional = deviceRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Device newDevice = zoneOptional.get();
                //TODO
                //newDevice.setName(zone.getName());
                return deviceRepository.save(newDevice);
            }
        }
        return deviceRepository.save(zone);
    }

    @Override
    public void deleteDeviceById(Long id) {
        Optional<Device> role = deviceRepository.findById(id);

        if (role.isPresent()) {
            deviceRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}

