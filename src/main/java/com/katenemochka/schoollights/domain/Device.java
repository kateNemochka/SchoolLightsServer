package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.DeviceType;
import com.katenemochka.schoollights.utils.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "devices")
@Setter
@Getter
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name="device_type_id", nullable=true)
    private DeviceType deviceType;
    @ManyToOne
    @JoinColumn(name="zone_id", nullable = true)
    private Zone zone;
    @ManyToOne
    @JoinColumn(name="row_id", nullable = true)
    private Row row;
    @ElementCollection
    @MapKeyColumn(name = "timestamp")
    @Convert(converter = LocalDateTimeAttributeConverter.class, attributeName = "key")
    private Map<LocalDateTime, Integer> data = new LinkedHashMap<>();
    private boolean dataIsBool;


    public Device() {
    }

    public Device(DeviceType deviceType) {
        this.deviceType = deviceType;
        this.dataIsBool = calculateDataIsBoolValue(deviceType);
    }

    public Device(DeviceType deviceType, Zone zone) {
        this.deviceType = deviceType;
        this.zone = zone;
        this.dataIsBool = calculateDataIsBoolValue(deviceType);
    }

    public Device(DeviceType deviceType, Zone zone, Row row) {
        this.deviceType = deviceType;
        this.zone = zone;
        this.row = row;
        this.dataIsBool = calculateDataIsBoolValue(deviceType);
    }

    private boolean calculateDataIsBoolValue(DeviceType deviceType) {
        return deviceType.getName().equals("MOTION_SENSOR")
                || deviceType.getName().equals("LIGHTS_ROW");
    }
}
