package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.RowRepository;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Row;
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

    @Override
    public List<Row> getAll() {
        List<Row> zones = rowRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Row getRowById(Long id) {
        return rowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Row createOrUpdate(Row zone) {
        if (zone.getId() != null) {

            Optional<Row> zoneOptional = rowRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Row newRow = zoneOptional.get();
                //TODO
                //newRow.setName(zone.getName());
                return rowRepository.save(newRow);
            }
        }
        return rowRepository.save(zone);
    }

    @Override
    public void deleteRowById(Long id) {
        Optional<Row> role = rowRepository.findById(id);

        if (role.isPresent()) {
            rowRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
