package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.Period;
import com.katenemochka.schoollights.service.PeriodService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;

    @Transactional
    public List<Period> getAll() {
        List<Period> periods = periodRepository.findAll();
        return periods.isEmpty() ? new ArrayList<>() : periods;
    }

    public Period getPeriodById(Long id) {
        return periodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("No period /w id " + id)));
    }

    public Period getPeriodByName(String name) {
        return periodRepository.findByName(name.toUpperCase())
                .orElseThrow(() -> new EntityNotFoundException("No period with name" + name));
    }

    public Period createOrUpdate(Period period) {
        if (period.getId() != null) {

            Optional<Period> periodOptional = periodRepository.findById(period.getId());

            if (periodOptional.isPresent()) {
                Period newPeriod = periodOptional.get();
                newPeriod.setName(period.getName());
                return periodRepository.save(newPeriod);
            }
        }
        return periodRepository.save(period);
    }

    public void deletePeriodById(Long id) {
        Optional<Period> period = periodRepository.findById(id);

        if (period.isPresent()) {
            periodRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no period with given id");
        }
    }

    public void loadDefaultData() {
        List<Period> defaultPeriods = new LinkedList<>();
        defaultPeriods.add(new Period("LESSON", "Урок"));
        defaultPeriods.add(new Period("BREAK", "Перерва"));
        defaultPeriods.add(new Period("PASSIVE", "Пасивний режим"));

        for (Period period : defaultPeriods) {
            try {
                Period existingPeriod = this.getPeriodByName(period.getName());
                period.setId(existingPeriod.getId());
            } catch (EntityNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            this.createOrUpdate(period);
        }
    }
}
