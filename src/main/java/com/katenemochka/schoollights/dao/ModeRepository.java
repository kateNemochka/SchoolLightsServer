package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.types.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeRepository extends JpaRepository<Mode, Long> {
    Mode findByName(String name);
}
