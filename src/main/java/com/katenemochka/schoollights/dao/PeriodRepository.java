package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PeriodRepository extends JpaRepository<Period, Long> {
    Optional<Period> findByName(String name);
}
