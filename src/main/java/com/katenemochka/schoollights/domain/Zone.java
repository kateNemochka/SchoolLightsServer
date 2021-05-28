package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.ControlType;
import com.katenemochka.schoollights.domain.types.ZoneType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
    @ManyToOne
    @JoinColumn(name="zone_type_id")
    private ZoneType zoneType;
    @OneToMany(mappedBy = "zone")
    private List<Device> deviceList;
    @OneToMany(mappedBy = "zone")
    private List<Row> rowList;
    @ManyToOne
    @JoinColumn(name="mode_id")
    private ControlType controlType;
    private int controlTimeout;
    private int dimmerValue;


    public Zone() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ZoneType getZoneType() {
        return zoneType;
    }

    public void setZoneType(ZoneType zoneType) {
        this.zoneType = zoneType;
    }

    public List<Device> getSensorList() {
        return deviceList;
    }

    public void setSensorList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public void setRowList(List<Row> rowList) {
        this.rowList = rowList;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public void setControlType(ControlType controlType) {
        this.controlType = controlType;
    }

    public int getControlTimeout() {
        return controlTimeout;
    }

    public void setControlTimeout(int controlTimeout) {
        this.controlTimeout = controlTimeout;
    }

    public int getDimmerValue() {
        return dimmerValue;
    }

    public void setDimmerValue(int dimmerValue) {
        this.dimmerValue = dimmerValue;
    }
}
