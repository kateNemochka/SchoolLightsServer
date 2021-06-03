package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.service.RowService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RowServiceImpl implements RowService {
    @Override
    public List<Row> getAll() {
        return null;
    }

    @Override
    public Row getRowById(Long id) {
        return null;
    }

    @Override
    public Row createOrUpdate(Row row) {
        return null;
    }

    @Override
    public void deleteRowById(Long id) {

    }
}
