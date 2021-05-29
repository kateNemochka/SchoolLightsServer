package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Zone;

import javax.persistence.*;
import java.util.List;

/*Спосіб керування освітленням*/
@Entity
@Table(name = "control_types")
public class ControlType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String displayName;
    @OneToMany(mappedBy = "controlType")
    List<Zone> zones;

    public ControlType() {
    }

    public ControlType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
