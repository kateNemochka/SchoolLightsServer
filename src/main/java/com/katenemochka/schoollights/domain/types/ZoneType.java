package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.Mode;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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
    @MapKeyJoinColumn(name="mode_id")
    Map<Mode, Integer> detectionIntervals;
    @ElementCollection
    @MapKeyJoinColumn(name="mode_id")
    Map<Mode, Integer> lightTimeouts;
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

    public Map<Mode, Integer> getDetectionIntervals() {
        return detectionIntervals;
    }

    public void setDetectionIntervals(Map<Mode, Integer> detectionIntervals) {
        this.detectionIntervals = detectionIntervals;
    }

    public Map<Mode, Integer> getLightTimeouts() {
        return lightTimeouts;
    }

    public void setLightTimeouts(Map<Mode, Integer> lightTimeouts) {
        this.lightTimeouts = lightTimeouts;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
