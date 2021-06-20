package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.DayOffRepository;
import com.katenemochka.schoollights.domain.DayOff;
import com.katenemochka.schoollights.service.DayOffService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class DayOffServiceImpl implements DayOffService {

    private DayOffRepository dayOffRepository;

    @Autowired
    public DayOffServiceImpl(DayOffRepository dayOffRepository) {
        this.dayOffRepository = dayOffRepository;
    }

    @Override
    public List<DayOff> getAll() {
        List<DayOff> daysOff = dayOffRepository.findAll();
        return daysOff.isEmpty() ? new ArrayList<>() : daysOff;
    }

    @Override
    public DayOff getDayOffById(Long id) {
        return dayOffRepository.getById(id);
    }

    @Override
    public DayOff createOrUpdate(DayOff dayOff) {
        if (dayOff.getId() != null) {
            Optional<DayOff> dayOffOptional = dayOffRepository.findById(dayOff.getId());
            if (dayOffOptional.isPresent()) {
                DayOff newDayOff = dayOffOptional.get();
                newDayOff.setStartDate(dayOff.getStartDate());
                newDayOff.setEndDate(dayOff.getEndDate());
                newDayOff.setType(dayOff.getType());
                return dayOffRepository.save(newDayOff);
            }
        }
        return dayOffRepository.save(dayOff);
    }

    @Override
    public void deleteDayOffById(Long id) {
        Optional<DayOff> dayOffOptional = dayOffRepository.findById(id);
        if (dayOffOptional.isPresent()) {
            dayOffRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no day off with given id");
        }
    }
}
