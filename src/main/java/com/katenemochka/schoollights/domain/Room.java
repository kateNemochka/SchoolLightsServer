package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.Period;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name; //207
    private int floor; //2
    private String purpose; //Кабінет інформатики
    private String description;
    private boolean isInner;
    @ManyToOne
    @JoinColumn(name="period_id")
    private Period period;
    @OneToMany(mappedBy = "room")
    private List<Zone> zones;
    @OneToOne(mappedBy = "room")
    private Microcontroller microcontroller;


    public Room() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public Microcontroller getMicrocontroller() {
        return microcontroller;
    }

    public void setMicrocontroller(Microcontroller microcontroller) {
        this.microcontroller = microcontroller;
    }

    public boolean isInner() {
        return isInner;
    }

    public void setInner(boolean inner) {
        isInner = inner;
    }
}
