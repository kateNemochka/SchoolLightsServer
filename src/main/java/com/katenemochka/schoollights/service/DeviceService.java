package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Room;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    Device getDeviceById(Long id);
    Device createOrUpdate(Device device);
    void deleteDeviceById(Long id);
}
