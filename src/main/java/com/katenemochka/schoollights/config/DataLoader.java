package com.katenemochka.schoollights.config;

import com.katenemochka.schoollights.dao.ControlTypeRepository;
import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.types.ControlType;
import com.katenemochka.schoollights.domain.types.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {

    private ControlTypeRepository controlTypeRepository;
    private PeriodRepository periodRepository;

    private Map<String, String> controlTypesMap;
    private Map<String, String> periodsMap;

    @Autowired
    public DataLoader(ControlTypeRepository controlTypeRepository, PeriodRepository periodRepository) {
        this.controlTypeRepository = controlTypeRepository;
        this.periodRepository = periodRepository;

        controlTypesMap = new HashMap<>();
        controlTypesMap.put("INIT", "Ініціалізація");
        controlTypesMap.put("ADAPTIVE", "Адаптивна освітленість");
        controlTypesMap.put("AUTO", "Автоматичний");
        controlTypesMap.put("FULL_POWER", "Повна потужність");
        controlTypesMap.put("MANUAL", "Ручний режим");
        controlTypesMap.put("OFF", "Вимкнути");
        controlTypesMap.put("PRESENTATION", "Режим презентації");

        periodsMap = new HashMap<>();
        periodsMap.put("LESSON", "Урок");
        periodsMap.put("BREAK", "Перерва");
        periodsMap.put("PASSIVE", "Пасивний режим");
    }

    public void run(ApplicationArguments args) {
        for (String controlTypeName : controlTypesMap.keySet()) {
            if (controlTypeRepository.findByName(controlTypeName) == null) {
                controlTypeRepository.save(new ControlType(controlTypeName, controlTypesMap.get(controlTypeName)));
            }
        }
        for (String modeName : periodsMap.keySet()) {
            if (periodRepository.findByName(modeName) == null) {
                periodRepository.save(new Period(modeName, periodsMap.get(modeName)));
            }
        }
    }
}
