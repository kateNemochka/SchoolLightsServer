package com.katenemochka.schoollights.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "device_types")
@Setter
@Getter
@NoArgsConstructor
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String displayName;
    private boolean onePerZone;

    public DeviceType(String name, String displayName, boolean onePerZone) {
        this.name = name;
        this.displayName = displayName;
        this.onePerZone = onePerZone;
    }
}
