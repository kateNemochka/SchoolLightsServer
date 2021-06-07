package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.domain.types.Period;

import java.util.List;

public interface PeriodService {
    List<Period> getAll();
    Period getPeriodById(Long id);
    Period getPeriodByName(String name);
    Period createOrUpdate(Period period);
    void deletePeriodById(Long id);
    void loadDefaultData();
}
