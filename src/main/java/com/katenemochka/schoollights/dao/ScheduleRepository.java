package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Schedule;
import com.katenemochka.schoollights.domain.types.EventType;
import com.katenemochka.schoollights.domain.types.Period;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByEventName(String name);
    Collection<Schedule> findAllByEventType(EventType eventType);
    Collection<Schedule> findAllByPeriod(Period period);
}