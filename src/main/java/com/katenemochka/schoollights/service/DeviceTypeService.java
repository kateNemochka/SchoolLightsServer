package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.DeviceType;

import java.util.List;

public interface DeviceTypeService {
    List<DeviceType> getAll();
    DeviceType getDeviceTypeById(Long id);
    DeviceType getDeviceTypeByName(String name);
    DeviceType createOrUpdate(DeviceType deviceType);
    void deleteDeviceTypeById(Long id);
    void loadDefaultData();
}
