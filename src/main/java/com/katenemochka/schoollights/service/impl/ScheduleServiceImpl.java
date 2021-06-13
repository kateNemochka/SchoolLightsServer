package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.ScheduleRepository;
import com.katenemochka.schoollights.domain.ScheduleEvent;
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

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleEvent> getAll() {
        List<ScheduleEvent> scheduleEvents = scheduleRepository.findAll();
        return scheduleEvents.isEmpty() ? new ArrayList<>() : scheduleEvents;
    }

    @Override
    public ScheduleEvent getScheduleEventById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No schedule /w id " + id)));
    }

    @Override
    public ScheduleEvent createOrUpdate(ScheduleEvent event) {
        if (event.getId() != null) {
            Optional<ScheduleEvent> optionalEvent = scheduleRepository.findById(event.getId());
            if (optionalEvent.isPresent()) {
                ScheduleEvent eventEntity = optionalEvent.get();
                eventEntity.setPeriodEnd(event.getPeriodEnd());
                eventEntity.setPeriod(event.getPeriod());
                eventEntity.updateExpression();
                return scheduleRepository.save(eventEntity);
            }
        }
        event.updateExpression();
        return scheduleRepository.save(event);
    }

    @Override
    public void deleteScheduleEventById(Long id) {
        Optional<ScheduleEvent> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            scheduleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no schedule event with given id");
        }
    }
}
