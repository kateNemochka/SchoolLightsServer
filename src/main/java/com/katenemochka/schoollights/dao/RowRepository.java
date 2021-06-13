package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Long> {
    List<Row> findAllByZoneOrderByRowNumberFromWindow(Zone zone);
    Row findByZoneAndRowNumberFromWindow(Zone zone, Integer numberFromWindow);
}
