package com.katenemochka.schoollights.config;

import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.types.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {

    private ModeRepository modeRepository;
    private PeriodRepository periodRepository;

    private Map<String, String> modesMap;
    private Map<String, String> periodsMap;

    @Autowired
    public DataLoader(ModeRepository modeRepository, PeriodRepository periodRepository) {
        this.modeRepository = modeRepository;
        this.periodRepository = periodRepository;

        modesMap = new HashMap<>();
        modesMap.put("INIT", "Ініціалізація");
        modesMap.put("ADAPTIVE", "Адаптивна освітленість");
        modesMap.put("AUTO", "Автоматичний");
        modesMap.put("FULL_POWER", "Повна потужність");
        modesMap.put("MANUAL", "Ручний режим");
        modesMap.put("OFF", "Вимкнути");
        modesMap.put("PRESENTATION", "Режим презентації");

        periodsMap = new HashMap<>();
        periodsMap.put("LESSON", "Урок");
        periodsMap.put("BREAK", "Перерва");
        periodsMap.put("PASSIVE", "Пасивний режим");
    }

    public void run(ApplicationArguments args) {
        for (String controlTypeName : modesMap.keySet()) {
            if (modeRepository.findByName(controlTypeName) == null) {
                modeRepository.save(new Mode(controlTypeName, modesMap.get(controlTypeName)));
            }
        }
        for (String modeName : periodsMap.keySet()) {
            if (periodRepository.findByName(modeName) == null) {
                periodRepository.save(new Period(modeName, periodsMap.get(modeName)));
            }
        }
    }
}
