package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.RowRepository;
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
        List<Row> rows = rowRepository.findAll();
        return rows.isEmpty() ? new ArrayList<>() : rows;
    }

    @Override
    public Row getRowById(Long id) {
        return rowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No row /w id " + id)));
    }

    @Override
    public Row createOrUpdate(Row row) {
        if (row.getId() != null) {

            Optional<Row> rowOptional = rowRepository.findById(row.getId());

            if (rowOptional.isPresent()) {
                Row newRow = rowOptional.get();
                //TODO
                //newRow.setName(row.getName());
                return rowRepository.save(newRow);
            }
        }
        return rowRepository.save(row);
    }

    @Override
    public void deleteRowById(Long id) {
        Optional<Row> role = rowRepository.findById(id);

        if (role.isPresent()) {
            rowRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no row type with given id");
        }
    }
}
