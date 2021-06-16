package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DeviceTypeRepository;
import com.katenemochka.schoollights.domain.DeviceType;
import com.katenemochka.schoollights.service.DeviceTypeService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Data
public class DeviceTypeServiceImpl implements DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;

    @Transactional
    public List<DeviceType> getAll() {
        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        return deviceTypes.isEmpty() ? new ArrayList<>() : deviceTypes;
    }

    public DeviceType getDeviceTypeById(Long id) {
        return deviceTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("No device type with id " + id)));
    }

    public DeviceType getDeviceTypeByName(String name) {
        return deviceTypeRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("No device type with name " + name));
    }

    public DeviceType createOrUpdate(DeviceType deviceType) {
        if (deviceType.getId() != null) {

            Optional<DeviceType> periodOptional = deviceTypeRepository.findById(deviceType.getId());

            if (periodOptional.isPresent()) {
                DeviceType newDeviceType = periodOptional.get();
                newDeviceType.setName(deviceType.getName());
                return deviceTypeRepository.save(deviceType);
            }
        }
        return deviceTypeRepository.save(deviceType);
    }

    public void deleteDeviceTypeById(Long id) {
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(id);

        if (deviceType.isPresent()) {
            deviceTypeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no device type with given id");
        }
    }

    public void loadDefaultData() {
        List<DeviceType> defaultDeviceTypes = new LinkedList<>();
        defaultDeviceTypes.add(new DeviceType("LIGHT_SENSOR", "Датчик освітленості", false));
        defaultDeviceTypes.add(new DeviceType("MOTION_SENSOR", "Датчик руху", true));
        defaultDeviceTypes.add(new DeviceType("LIGHTS_ROW", "Ряд світильників", false));
        defaultDeviceTypes.add(new DeviceType("DIMMER", "Димер", false));

        for (DeviceType deviceType : defaultDeviceTypes) {
            try {
                DeviceType existingDeviceType = this.getDeviceTypeByName(deviceType.getName());
                deviceType.setId(existingDeviceType.getId());
            } catch (EntityNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            this.createOrUpdate(deviceType);
        }
    }
}
