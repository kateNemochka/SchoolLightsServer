package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.types.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeRepository extends JpaRepository<Mode, Long> {
    Optional<Mode> findByName(String name);
}
