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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
}
