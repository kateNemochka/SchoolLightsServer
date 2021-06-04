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
        List<Schedule> zones = scheduleRepository.findAll();
        return zones.isEmpty() ? new ArrayList<>() : zones;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No zone /w id " + id)));
    }

    @Override
    public Schedule createOrUpdate(Schedule zone) {
        if (zone.getId() != null) {

            Optional<Schedule> zoneOptional = scheduleRepository.findById(zone.getId());

            if (zoneOptional.isPresent()) {
                Schedule newSchedule = zoneOptional.get();
                //TODO
                //newSchedule.setName(zone.getName());
                return scheduleRepository.save(newSchedule);
            }
        }
        return scheduleRepository.save(zone);
    }

    @Override
    public void deleteScheduleById(Long id) {
        Optional<Schedule> role = scheduleRepository.findById(id);

        if (role.isPresent()) {
            scheduleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no zone type with given id");
        }
    }
}
