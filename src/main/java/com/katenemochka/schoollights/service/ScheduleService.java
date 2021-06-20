package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.ScheduleEvent;

import java.util.List;

public interface ScheduleService {
    List<ScheduleEvent> getAll();
    ScheduleEvent getScheduleEventById(Long id);
    ScheduleEvent createOrUpdate(ScheduleEvent scheduleEvent);
    void deleteScheduleEventById(Long id);
}
