package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DeviceRepository;
import com.katenemochka.schoollights.dao.DeviceTypeRepository;
import com.katenemochka.schoollights.dao.RowRepository;
import com.katenemochka.schoollights.dao.ZoneRepository;
import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.service.DeviceService;
import com.katenemochka.schoollights.service.RowService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class RowServiceImpl implements RowService {
    @Autowired
    RowRepository rowRepository;
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceTypeRepository deviceTypeRepository;
    @Autowired
    DeviceService deviceService;

    @Override
    public List<Row> getAll() {
        List<Row> rows = rowRepository.findAll();
        return rows.isEmpty() ? new ArrayList<>() : rows;
    }

    @Override
    public Row getRowById(Long id) {
        return rowRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(("No row /w id " + id)));
    }

    @Override
    public Row createOrUpdate(Row row) {
        if (row.getId() != null) {
            Optional<Row> rowOptional = rowRepository.findById(row.getId());
            if (rowOptional.isPresent()) {
                Row newRow = rowOptional.get();
                newRow.setRowNumberFromWindow(newRow.getRowNumberFromWindow());
                return rowRepository.save(newRow);
            }
        }
        return rowRepository.save(row);
    }

    @Override
    public void deleteRowById(Long id) {
        Row row = rowRepository.findById(id).orElseThrow();
        System.out.println(row);
        Optional<Zone> zoneOptional = zoneRepository.findById(row.getZone().getId());
        if (zoneOptional.isPresent()) {
            Zone zone = zoneOptional.get();
            System.out.println(zone);
            zone.getRows().remove(row);
            System.out.println("removed row from zone");
            zone.getDevices().removeAll(row.getDevices());
            System.out.println("removed devices from zone");
            zoneRepository.save(zone);
        }
        System.out.println("row deleted");
    }

    @Override
    public Zone addRowToZone(Row row, Zone zone) {
        Zone zoneEntity = zoneRepository.getById(zone.getId());
        if (row.getId() == null
                || zone.getRows().stream().noneMatch(r -> r.getId().equals(row.getId()))) {
            row.setZone(zoneEntity);
            rowRepository.save(row);
            row.getDevices().forEach(d -> deviceService.addDeviceToZone(d, zoneEntity));
            zoneEntity.getRows().add(row);
            return zoneRepository.save(zoneEntity);
        }
        return zone;
    }

    @Override
    public Row createDefaultRow() {
        Row row = new Row(1);
        rowRepository.save(row);
        List<String> deviceTypes = List.of("LIGHT_SENSOR", "DIMMER", "LIGHTS_ROW");
        for (String deviceType : deviceTypes) {
            Device device = new Device(deviceTypeRepository.findByName(deviceType).orElseThrow());
            device.setRow(row);
            deviceRepository.save(device);
            row.getDevices().add(device);
        }
        return rowRepository.save(row);
    }
}
