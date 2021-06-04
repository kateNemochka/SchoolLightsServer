package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.service.PeriodService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public List<Period> getAll() {
        List<Period> periods = periodRepository.findAll();
        return periods.isEmpty() ? new ArrayList<>() : periods;
    }

    @Override
    public Period getPeriodById(Long id) {
        return periodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No period /w id " + id)));
    }

    @Override
    public Period createOrUpdate(Period period) {
        if (period.getId() != null) {

            Optional<Period> periodOptional = periodRepository.findById(period.getId());

            if (periodOptional.isPresent()) {
                Period newRole = periodOptional.get();
                newRole.setName(period.getName());
                return periodRepository.save(newRole);
            }
        }
        return periodRepository.save(period);
    }

    @Override
    public void deletePeriodById(Long id) {
        Optional<Period> role = periodRepository.findById(id);

        if (role.isPresent()) {
            periodRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no period with given id");
        }
    }
}
