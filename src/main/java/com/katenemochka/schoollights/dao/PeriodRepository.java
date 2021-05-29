package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.types.Period;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeriodRepository extends JpaRepository<Period, Long> {
    Period findByName(String name);
}
