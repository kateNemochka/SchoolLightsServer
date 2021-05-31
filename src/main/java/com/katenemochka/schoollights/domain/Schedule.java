package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.EventType;
import com.katenemochka.schoollights.domain.types.Period;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_entries")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpressionFromTime() {
        return "0 " + periodStart.getMinute() + " " + periodStart.getHour() + " * * 1-5";
    }

    public LocalTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalTime periodStart) {
        this.periodStart = periodStart;
    }
}
