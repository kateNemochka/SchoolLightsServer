package com.katenemochka.schoollights.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name; //207
    private String mqttName;
    private int floor; //2
    private String purpose; //Кабінет інформатики
    private String description;
    private boolean innerRoom;
    private int colorTemperature; //K
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones = new ArrayList<>();
    @OneToOne(mappedBy = "room")
    private Microcontroller microcontroller;

    public Room() {
        colorTemperature = 3000;
    }
}
