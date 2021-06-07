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
        List<Device> devices = deviceRepository.findAll();
        return devices.isEmpty() ? new ArrayList<>() : devices;
    }

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No device /w id " + id)));
    }

    @Override
    public Device createOrUpdate(Device device) {
        if (device.getId() != null) {

            Optional<Device> deviceOptional = deviceRepository.findById(device.getId());

            if (deviceOptional.isPresent()) {
                Device newDevice = deviceOptional.get();
                //TODO
                //newDevice.setName(device.getName());
                return deviceRepository.save(newDevice);
            }
        }
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);

        if (device.isPresent()) {
            deviceRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no device type with given id");
        }
    }
}

