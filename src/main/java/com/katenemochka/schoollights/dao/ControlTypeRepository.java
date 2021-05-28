package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.types.ControlType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlTypeRepository extends JpaRepository<ControlType, Long> {
    ControlType findByName(String name);
}
