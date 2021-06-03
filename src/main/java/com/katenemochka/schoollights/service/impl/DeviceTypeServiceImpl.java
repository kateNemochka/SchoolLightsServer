package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.types.DeviceType;
import com.katenemochka.schoollights.service.DeviceTypeService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DeviceTypeServiceImpl implements DeviceTypeService {
    @Override
    public List<DeviceType> getAll() {
        return null;
    }

    @Override
    public DeviceType getDeviceTypeById(Long id) {
        return null;
    }

    @Override
    public DeviceType createOrUpdate(DeviceType deviceType) {
        return null;
    }

    @Override
    public void deleteDeviceTypeById(Long id) {

    }
}
