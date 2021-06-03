package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Row;

import java.util.List;

public interface RowService {
    List<Row> getAll();
    Row getRowById(Long id);
    Row createOrUpdate(Row row);
    void deleteRowById(Long id);
}
