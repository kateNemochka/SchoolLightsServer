package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAll();
    Schedule getScheduleById(Long id);
    Schedule createOrUpdate(Schedule schedule);
    void deleteScheduleById(Long id);
}
