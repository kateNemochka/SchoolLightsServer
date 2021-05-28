package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.DeviceType;
import com.katenemochka.schoollights.utils.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeviceType getSensorType() {
        return deviceType;
    }

    public void setSensorType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Map<LocalDateTime, Integer> getData() {
        return data;
    }

    public void setData(Map<LocalDateTime, Integer> data) {
        this.data = data;
    }

    public boolean isDataIsBool() {
        return dataIsBool;
    }

    public void setDataIsBool(boolean dataIsBool) {
        this.dataIsBool = dataIsBool;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }
}
