package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    /*List<Device> getDevicesByType();
    List<Device> getDevicesByRoom();
    List<Device> getDevicesByZone();
    List<Device> getDevicesByRow();*/
    Device getDeviceById(Long id);
    Device createOrUpdate(Device device);
    void deleteDeviceById(Long id);
    Zone addDeviceToZone(Device device, Zone zone);
    Row addDeviceToRow(Device device, Row row);
}
