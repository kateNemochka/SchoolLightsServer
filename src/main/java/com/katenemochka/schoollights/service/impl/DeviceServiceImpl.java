package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.service.DeviceService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DeviceServiceImpl implements DeviceService {
    @Override
    public List<Device> getAll() {
        return null;
    }

    @Override
    public Device getDeviceById(Long id) {
        return null;
    }

    @Override
    public Device createOrUpdate(Device device) {
        return null;
    }

    @Override
    public void deleteDeviceById(Long id) {

    }
}

