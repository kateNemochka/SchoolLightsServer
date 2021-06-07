package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ScheduleRepository;
import com.katenemochka.schoollights.domain.Schedule;
import com.katenemochka.schoollights.service.ScheduleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.isEmpty() ? new ArrayList<>() : schedules;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No schedule /w id " + id)));
    }

    @Override
    public Schedule createOrUpdate(Schedule schedule) {
        if (schedule.getId() != null) {

            Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());

            if (scheduleOptional.isPresent()) {
                Schedule newSchedule = scheduleOptional.get();
                //TODO
                //newSchedule.setName(zone.getName());
                return scheduleRepository.save(newSchedule);
            }
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);

        if (schedule.isPresent()) {
            scheduleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no schedule type with given id");
        }
    }
}
