package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
    Optional<DeviceType> findByName(String name);
}
