package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.*;
import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.service.RowService;
import com.katenemochka.schoollights.service.ZoneService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    ModeRepository modeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    DeviceTypeRepository deviceTypeRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RowService rowService;


    @Override
    public List<Zone> getAll() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public List<Zone> getZonesByRoom(Room room) {
        List<Zone> zones = zoneRepository.findAllByRoom(room);
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(("No zone with id " + id)));
    }

    @Override
    public Zone createOrUpdate(Zone zone) {
        if (zone.getId() != null) {
            Zone zoneEntity = zoneRepository.getById(zone.getId());
            zoneEntity.setName(zone.getName());
            zoneEntity.setZoneType(zone.getZoneType());
            return zoneRepository.save(zoneEntity);
        }
        Zone savedZone = zoneRepository.save(zone);
        Row defaultRow = rowService.createDefaultRow();
        return rowService.addRowToZone(defaultRow, savedZone);
    }

    @Override
    public Room addZoneToRoom(Zone zone, Room room) {
        if (zone.getId() == null
                || room.getZones().stream().noneMatch(z -> z.getId().equals(zone.getId()))) {
            Room roomEntity = roomRepository.getById(room.getId());
            zone.setRoom(roomEntity);
            zoneRepository.save(zone);
            roomEntity.getZones().add(zone);
            return roomRepository.save(roomEntity);
        }
        return room;
    }

    @Override
    public void deleteZoneById(Long id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public Zone createDefaultZone() {
        Zone zone = new Zone();
        zone.setName("Базова зона");
        zoneRepository.save(zone);
        Device device = new Device(deviceTypeRepository.findByName("MOTION_SENSOR").orElseThrow());
        device.setZone(zone);
        deviceRepository.save(device);
        zone.getDevices().add(device);
        zoneRepository.save(zone);
        Row row = rowService.createDefaultRow();
        return rowService.addRowToZone(row, zone);
    }
}
