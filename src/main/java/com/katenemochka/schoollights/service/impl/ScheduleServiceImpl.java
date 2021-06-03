package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.Schedule;
import com.katenemochka.schoollights.service.ScheduleService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public List<Schedule> getAll() {
        return null;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return null;
    }

    @Override
    public Schedule createOrUpdate(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteScheduleById(Long id) {

    }
}
