package com.katenemochka.schoollights.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "days_off")
@Data
@NoArgsConstructor
public class DayOff implements Comparable<DayOff> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private DayOffType type;


    public DayOff(DayOffType type) {
        this.type = type;
    }

    @Override
    public int compareTo(@NotNull DayOff o) {
        if (this.startDate != null && o.startDate != null)
            return this.startDate.compareTo(o.startDate);
        return 0;
    }
}
