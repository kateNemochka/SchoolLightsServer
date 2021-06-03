package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name; //207
    private int floor; //2
    private String purpose; //Кабінет інформатики
    private String description;
    private boolean isInner;
    private int colorTemperature;
    @ManyToOne
    @JoinColumn(name="period_id")
    private Period period;
    @OneToMany(mappedBy = "room")
    private List<Zone> zones;
    @OneToOne(mappedBy = "room")
    private Microcontroller microcontroller;


    public Room() {
    }
}
