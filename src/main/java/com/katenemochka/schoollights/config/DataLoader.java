package com.katenemochka.schoollights.config;

import com.katenemochka.schoollights.dao.ControlTypeRepository;
import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.domain.types.ControlType;
import com.katenemochka.schoollights.domain.types.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {

    private ControlTypeRepository controlTypeRepository;
    private ModeRepository modeRepository;

    private Map<String, String> controlTypesMap;
    private Map<String, String> modesMap;

    @Autowired
    public DataLoader(ControlTypeRepository controlTypeRepository, ModeRepository modeRepository) {
        this.controlTypeRepository = controlTypeRepository;
        this.modeRepository = modeRepository;

        controlTypesMap = new HashMap<>();
        controlTypesMap.put("INIT", "Ініціалізація");
        controlTypesMap.put("ADAPTIVE", "Адаптивна освітленість");
        controlTypesMap.put("AUTO", "Автоматичний");
        controlTypesMap.put("FULL_POWER", "Повна потужність");
        controlTypesMap.put("MANUAL", "Ручний режим");
        controlTypesMap.put("OFF", "Вимкнути");
        controlTypesMap.put("PRESENTATION", "Режим презентації");

        modesMap = new HashMap<>();
        modesMap.put("LESSON", "Урок");
        modesMap.put("BREAK", "Перерва");
        modesMap.put("PASSIVE", "Пасивний режим");
    }

    public void run(ApplicationArguments args) {
        for (String controlTypeName : controlTypesMap.keySet()) {
            if (controlTypeRepository.findByName(controlTypeName) != null) {
                controlTypeRepository.save(new ControlType(controlTypeName, controlTypesMap.get(controlTypeName)));
            }
        }
        for (String modeName : modesMap.keySet()) {
            if (modeRepository.findByName(modeName) != null) {
                modeRepository.save(new Mode(modeName, modesMap.get(modeName)));
            }
        }
    }
}
