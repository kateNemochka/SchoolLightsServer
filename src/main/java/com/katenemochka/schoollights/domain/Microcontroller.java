package com.katenemochka.schoollights.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "microcontrollers")
@Setter
@Getter
public class Microcontroller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private String macAddress;
    private String mqttUsername;
    private String mqttPassword;
    private Date modeUpdate;
    private Date firmwareUpdate;
    private int sensorsUpdateTimeout; //хв - інтервал між сповіщеннями про дані датчиків
    private int statusUpdateTimeout; //хв - інтервал між сповіщеннями про статус контролера
    private boolean deviceConnected;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Microcontroller() {
        this.sensorsUpdateTimeout = 5;
        this.statusUpdateTimeout = 120;
        this.deviceConnected = false;
    }

    public Microcontroller(String macAddress) {
        this.macAddress = macAddress;
        this.sensorsUpdateTimeout = 5;
        this.statusUpdateTimeout = 120;
        this.deviceConnected = true;
    }
}
