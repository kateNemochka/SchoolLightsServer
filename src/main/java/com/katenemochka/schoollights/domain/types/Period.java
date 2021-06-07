package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Schedule;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "periods")
@Setter
@Getter
public class Period implements Comparable<Period> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
