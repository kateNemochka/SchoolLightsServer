package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.DeviceType;
import com.katenemochka.schoollights.utils.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "devices")
@Setter
@Getter
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String deviceName;
    @ManyToOne
    @JoinColumn(name="device_type_id", nullable=false)
    private DeviceType deviceType;
    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;
    @ManyToOne
    @JoinColumn(name="row_id")
    private Row row;
    @ElementCollection
    @MapKeyColumn(name = "timestamp")
    @Convert(converter = LocalDateTimeAttributeConverter.class, attributeName = "key")
    private Map<LocalDateTime, Integer> data;
    private boolean dataIsBool;


    public Device() {
    }
}
