package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DeviceRepository;
import com.katenemochka.schoollights.dao.RowRepository;
import com.katenemochka.schoollights.dao.ZoneRepository;
import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
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
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    RowRepository rowRepository;

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
                newDevice.setDeviceType(device.getDeviceType());
                return deviceRepository.save(newDevice);
            }
        }
        return deviceRepository.save(device);
    }

    @Override
    public Zone addDeviceToZone(Device device, Zone zone) {
        Zone zoneEntity = zoneRepository.getById(zone.getId());
        if (device.getId() == null
                || zone.getDevices().stream().noneMatch(d -> d.getId().equals(device.getId()))) {
            device.setZone(zoneEntity);
            deviceRepository.save(device);
            zoneEntity.getDevices().add(device);
            return zoneRepository.save(zoneEntity);
        }
        return zoneEntity;
    }

    @Override
    public Row addDeviceToRow(Device device, Row row) {
        Row rowEntity = rowRepository.getById(row.getId());
        if (device.getId() == null
                || row.getDevices().stream().noneMatch(d -> d.getId().equals(device.getId()))) {
            device.setRow(rowEntity);
            deviceRepository.save(device);
            rowEntity.getDevices().add(device);
            return rowRepository.save(rowEntity);
        }
        return row;
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

