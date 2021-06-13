package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByRoom(Room room);
}
