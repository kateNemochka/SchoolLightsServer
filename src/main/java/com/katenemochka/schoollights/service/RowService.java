package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;

import java.util.List;

public interface RowService {
    List<Row> getAll();
    Row getRowById(Long id);
    Row createOrUpdate(Row row);
    void deleteRowById(Long id);
    Zone addRowToZone(Row row, Zone zone);
    Row createDefaultRow();
}
