package com.katenemochka.schoollights.config;

import com.katenemochka.schoollights.dao.DeviceTypeRepository;
import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.service.DeviceTypeService;
import com.katenemochka.schoollights.service.ModeService;
import com.katenemochka.schoollights.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    DeviceTypeService deviceTypeService;
    @Autowired
    ModeService modeService;
    @Autowired
    PeriodService periodService;

    @Autowired
    public DataLoader() {}

    public void run(ApplicationArguments args) {
        deviceTypeService.loadDefaultData();
        modeService.loadDefaultData();
        periodService.loadDefaultData();
    }
}
