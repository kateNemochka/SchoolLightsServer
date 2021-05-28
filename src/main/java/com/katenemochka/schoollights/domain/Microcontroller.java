package com.katenemochka.schoollights.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "microcontrollers")
public class Microcontroller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clientId;
    private String ipAddress;
    private String macAddress;
    private String mqttUsername;
    private Date modeUpdate;
    private Date firmwareUpdate;
    private int sensorsUpdateTimeout; //інтервал між сповіщеннями про дані датчиків
    private int statusUpdateTimeout; //інтервал між сповіщеннями про статус контролера
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;


    public Microcontroller() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public void setMqttUsername(String mqttUsername) {
        this.mqttUsername = mqttUsername;
    }

    public Date getModeUpdate() {
        return modeUpdate;
    }

    public void setModeUpdate(Date modeUpdate) {
        this.modeUpdate = modeUpdate;
    }

    public Date getFirmwareUpdate() {
        return firmwareUpdate;
    }

    public void setFirmwareUpdate(Date firmwareUpdate) {
        this.firmwareUpdate = firmwareUpdate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getSensorsUpdateTimeout() {
        return sensorsUpdateTimeout;
    }

    public void setSensorsUpdateTimeout(int sensorsUpdateTimeout) {
        this.sensorsUpdateTimeout = sensorsUpdateTimeout;
    }

    public int getStatusUpdateTimeout() {
        return statusUpdateTimeout;
    }

    public void setStatusUpdateTimeout(int statusUpdateTimeout) {
        this.statusUpdateTimeout = statusUpdateTimeout;
    }
}
