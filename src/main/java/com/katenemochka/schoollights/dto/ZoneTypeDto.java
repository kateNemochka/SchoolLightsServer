package com.katenemochka.schoollights.dto;

import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.domain.types.ZoneType;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ZoneTypeDto {

    private Long id;
    private String name;
    private String description;
    private int lightMinimum;
    private int lightMaximum;

    Map<String, Integer> detectionIntervals;
    Map<String, Integer> lightTimeouts;

    public ZoneTypeDto() {
        detectionIntervals = new LinkedHashMap<>();
        lightTimeouts = new LinkedHashMap<>();
    }

    public ZoneTypeDto getDtoFromZoneType(ZoneType zoneType, Map<String, Period> periodByName) {
        if (zoneType.getId() != null)
            this.id = zoneType.getId();
        this.name = zoneType.getName();
        this.description = zoneType.getDescription();
        this.lightMinimum = zoneType.getLightMinimum();
        this.lightMaximum = zoneType.getLightMaximum();

        System.out.println(periodByName);

        if (zoneType.getDetectionIntervals() == null
                || zoneType.getDetectionIntervals().isEmpty()) {
            this.detectionIntervals = periodByName.keySet().stream()
                    .collect(Collectors.toMap(k -> k, i -> 0));
        } else {
            zoneType.getDetectionIntervals()
                    .forEach((key, value) -> detectionIntervals.put((key).getName(), value));
        }

        if (zoneType.getLightTimeouts() == null
                || zoneType.getLightTimeouts().isEmpty()) {
            this.lightTimeouts = periodByName.keySet().stream()
                    .collect(Collectors.toMap(k -> k, i -> 0));
        } else {
            zoneType.getLightTimeouts()
                    .forEach((key, value) -> lightTimeouts.put((key).getName(), value));
        }
        System.out.println("GETTING DTO FROM ZONE TYPE");
        System.out.println(zoneType);
        System.out.println(this);
        return this;
    }

    public ZoneTypeDto getDtoFromZoneType(ZoneType zoneType) {
        if (zoneType.getId() != null)
            this.id = zoneType.getId();
        this.name = zoneType.getName();
        this.description = zoneType.getDescription();
        this.lightMinimum = zoneType.getLightMinimum();
        this.lightMaximum = zoneType.getLightMaximum();

        zoneType.getDetectionIntervals()
                .forEach((key, value) -> detectionIntervals.put(key.getName(), value));
        zoneType.getLightTimeouts()
                .forEach((key, value) -> lightTimeouts.put(key.getName(), value));
        System.out.println("GETTING DTO FROM ZONE TYPE (EXISTING)");
        System.out.println(zoneType);
        System.out.println(this);
        return this;
    }

    public ZoneType convertZoneTypeDtoToZoneType(Map<String, Period> periodByName) {
        ZoneType zoneType = new ZoneType();
        if (this.id != null)
            zoneType.setId(this.id);
        zoneType.setName(this.name);
        zoneType.setLightMinimum(this.lightMinimum);
        zoneType.setLightMaximum(this.lightMaximum);
        zoneType.setDescription(this.description);

        Map<Period, Integer> detectionIntervalsSet = new LinkedHashMap<>();
        this.detectionIntervals.forEach((key, value) -> detectionIntervalsSet.put(
                periodByName.get(key), value));
        zoneType.setDetectionIntervals(detectionIntervalsSet);

        Map<Period, Integer> lightTimeoutsSet = new LinkedHashMap<>();
        this.lightTimeouts.forEach((key, value) -> lightTimeoutsSet.put(
                periodByName.get(key), value));
        zoneType.setLightTimeouts(lightTimeoutsSet);
        System.out.println("GETTING ZONE TYPE FROM DTO");
        System.out.println(this);
        System.out.println(zoneType);
        return zoneType;
    }
}
