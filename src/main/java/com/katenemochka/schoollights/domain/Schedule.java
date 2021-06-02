package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.EventType;
import com.katenemochka.schoollights.domain.types.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_entries")
@Setter
@Getter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String eventName;
    @Column
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    @Column
    private LocalTime periodStart;
    @ManyToOne
    @JoinColumn(name = "period_id")
    Period period;
    /*0 8 * * 1-5*/
    @Column
    private String expression;

    public Schedule()  {
    }

    public Schedule(String eventName, EventType eventType, LocalTime periodStart, Period period) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.periodStart = periodStart;
        this.period = period;
        this.expression = "0 " + periodStart.getMinute() + " " + periodStart.getHour() + " * * 1-5";
    }
}
