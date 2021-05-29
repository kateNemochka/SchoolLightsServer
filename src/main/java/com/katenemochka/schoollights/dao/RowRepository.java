package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RowRepository extends JpaRepository<Row, Long> {
    Collection<Row> findAllByZone(Zone zone);
    Row findByZoneAndRowNumberFromWindow(Zone zone, Integer numberFromWindow);
}
