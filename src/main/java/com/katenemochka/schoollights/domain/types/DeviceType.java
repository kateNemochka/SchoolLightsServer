package com.katenemochka.schoollights.domain.types;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "device_types")
public class DeviceType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private boolean onePerZone;


    public DeviceType() {
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

    public boolean isOnePerZone() {
        return onePerZone;
    }

    public void setOnePerZone(boolean onePerZone) {
        this.onePerZone = onePerZone;
    }

}
