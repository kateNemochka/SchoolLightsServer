package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Schedule;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!Objects.equals(id, period.id)) return false;
        if (!Objects.equals(name, period.name)) return false;
        return Objects.equals(displayName, period.displayName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }
}
