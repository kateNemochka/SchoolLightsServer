package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DeviceTypeRepository;
import com.katenemochka.schoollights.domain.types.DeviceType;
import com.katenemochka.schoollights.service.DeviceTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Override
    public List<DeviceType> getAll() {
        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        return deviceTypes.isEmpty() ? new ArrayList<>() : deviceTypes;
    }

    @Override
    public DeviceType getDeviceTypeById(Long id) {
        return deviceTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No period /w id " + id)));
    }

    @Override
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

    @Override
    public void deleteDeviceTypeById(Long id) {
        Optional<DeviceType> role = deviceTypeRepository.findById(id);

        if (role.isPresent()) {
            deviceTypeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no device type with given id");
        }
    }
}
