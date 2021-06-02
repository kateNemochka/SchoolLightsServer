package com.katenemochka.schoollights.domain.types;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "device_types")
@Setter
@Getter
public class DeviceType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private boolean onePerZone;


    public DeviceType() {
    }
}
