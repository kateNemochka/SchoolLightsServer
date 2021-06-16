package com.katenemochka.schoollights.domain;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_entries")
@Data
public class ScheduleEvent implements Comparable<ScheduleEvent> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime periodStart;
    private LocalTime periodEnd;
    @ManyToOne
    @JoinColumn(name = "period_id")
    Period period;
    /*0 8 * * 1-5*/
    @Column
    private String expression;

    public ScheduleEvent()  {
    }

    public ScheduleEvent(LocalTime periodStart, LocalTime periodEnd, Period period) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.period = period;
        this.expression = "0 " + periodStart.getMinute() + " " + periodStart.getHour() + " * * 1-5";
    }

    public void updateExpression() {
        this.expression = "0 " + periodStart.getMinute() + " " + periodStart.getHour() + " * * 1-5";
    }

    @Override
    public int compareTo(@NotNull ScheduleEvent o) {
        return this.periodStart.compareTo(o.periodStart);
    }
}
