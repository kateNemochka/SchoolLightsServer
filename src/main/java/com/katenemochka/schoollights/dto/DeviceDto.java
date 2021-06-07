package com.katenemochka.schoollights.dto;

import com.katenemochka.schoollights.domain.Device;
import lombok.Data;

@Data
public class DeviceDto {
    private Long id;
    private String deviceName;
    private String deviceType;
    private String room;
    private String zone;
    private int row;
    private boolean dataIsBool;

    public DeviceDto(Device device) {
        this.id = device.getId();
        this.deviceName = device.getDeviceName();
        this.deviceType = device.getDeviceType().getDisplayName();
        this.room = device.getZone().getRoom().getName();
        this.zone = device.getZone().getName();
        this.row = device.getRow().getRowNumberFromWindow();
        this.dataIsBool = device.isDataIsBool();
    }

}
