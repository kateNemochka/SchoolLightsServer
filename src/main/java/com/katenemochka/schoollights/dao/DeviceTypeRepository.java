package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.types.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
}
