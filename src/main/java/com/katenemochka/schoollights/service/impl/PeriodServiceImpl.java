package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.service.PeriodService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PeriodServiceImpl implements PeriodService {
    @Override
    public List<Period> getAll() {
        return null;
    }

    @Override
    public Period getPeriodById(Long id) {
        return null;
    }

    @Override
    public Period createOrUpdate(Period period) {
        return null;
    }

    @Override
    public void deletePeriodById(Long id) {

    }
}
