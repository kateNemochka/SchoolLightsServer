package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.ScheduleEvent;
import com.katenemochka.schoollights.domain.types.EventType;
import com.katenemochka.schoollights.domain.types.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEvent, Long> {
    List<ScheduleEvent> findAllByPeriod(Period period);
}