package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Collection<Device> findAllByDeviceType(DeviceType deviceType);
    Collection<Device> findAllByZone(Zone zone);
    Collection<Device> findAllByZoneAndRow(Zone zone, Row row);
}
