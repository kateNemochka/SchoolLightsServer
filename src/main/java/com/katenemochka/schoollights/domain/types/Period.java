package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Schedule;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "periods")
public class Period implements Comparable<Period> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String displayName;
    @OneToMany(mappedBy = "period")
    List<Room> rooms;
    @OneToMany(mappedBy = "period")
    List<Schedule> scheduleEntries;

    public Period() {
    }

    public Period(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int compareTo(@NotNull Period period) {
        if (this.id > period.getId())
            return 1;
        else if (this.id < period.getId())
            return -1;
        return 0;
    }

    public List<Schedule> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(List<Schedule> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }
}
