package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.DayOff;
import com.katenemochka.schoollights.domain.DayOffType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {
    List<DayOff> findAllByType(DayOffType type);
}
