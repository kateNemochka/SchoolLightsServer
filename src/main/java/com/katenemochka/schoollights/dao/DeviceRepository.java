package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByDeviceType(DeviceType deviceType);
    List<Device> findAllByZone(Zone zone);
    List<Device> findAllByRow(Row row);
    List<Device> findAllByDeviceTypeAndZone(DeviceType deviceType, Zone zone);
    List<Device> findAllByDeviceTypeAndRow(DeviceType deviceType, Row row);
}
