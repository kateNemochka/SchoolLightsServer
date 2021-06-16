package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.DayOff;
import com.katenemochka.schoollights.domain.ScheduleEvent;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DayOffService {
    List<DayOff> getAll();
    DayOff getDayOffById(Long id);
    DayOff createOrUpdate(DayOff dayOff);
    void deleteDayOffById(Long id);
}
