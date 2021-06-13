package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.types.ModeTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModeTimestampRepository extends JpaRepository<ModeTimestamp, Long> {
    List<ModeTimestamp> findAllByZone(Zone zone);
    List<ModeTimestamp> findAllByZoneAndMode(Zone zone, Mode mode);
    Optional<ModeTimestamp> findFirstByZoneOrderByTimestampDesc(Zone zone);
}
