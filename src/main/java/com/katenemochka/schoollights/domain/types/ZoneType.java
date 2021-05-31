package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Zone;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "zone_types")
public class ZoneType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private int lightMinimum;
    private int lightMaximum;
    @ElementCollection
    @MapKeyJoinColumn(name="period_id")
    Map<Period, Integer> detectionIntervals;
    @ElementCollection
    @MapKeyJoinColumn(name="period_id")
    Map<Period, Integer> lightTimeouts;
    @OneToMany(mappedBy = "zoneType")
    List<Zone> zones;


    public ZoneType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLightMinimum() {
        return lightMinimum;
    }

    public void setLightMinimum(int lightMinimum) {
        this.lightMinimum = lightMinimum;
    }

    public int getLightMaximum() {
        return lightMaximum;
    }

    public void setLightMaximum(int lightMaximum) {
        this.lightMaximum = lightMaximum;
    }

    public Map<Period, Integer> getDetectionIntervals() {
        return detectionIntervals;
    }

    public List<Integer> getDetectionIntervalsSorted() {
        return detectionIntervals.keySet()
                .stream()
                .sorted(Period::compareTo)
                .mapToInt(p -> detectionIntervals.get(p))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public void setDetectionIntervals(Map<Period, Integer> detectionIntervals) {
        this.detectionIntervals = detectionIntervals;
    }

    public Map<Period, Integer> getLightTimeouts() {
        return lightTimeouts;
    }

    public List<Integer> getLightTimeoutsSorted() {
        return lightTimeouts.keySet()
                .stream()
                .sorted(Period::compareTo)
                .mapToInt(p -> lightTimeouts.get(p))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public void setLightTimeouts(Map<Period, Integer> lightTimeouts) {
        this.lightTimeouts = lightTimeouts;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
