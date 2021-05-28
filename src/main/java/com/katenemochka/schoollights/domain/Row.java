package com.katenemochka.schoollights.domain;

import javax.persistence.*;

@Entity
@Table(name="lights_row")
public class Row {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int rowNumberFromWindow;
    private int customDimValue;
    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;

    public Row() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public int getRowNumberFromWindow() {
        return rowNumberFromWindow;
    }

    public void setRowNumberFromWindow(int rowNumberFromWindow) {
        this.rowNumberFromWindow = rowNumberFromWindow;
    }

    public int getCustomDimValue() {
        return customDimValue;
    }

    public void setCustomDimValue(int customDimValue) {
        this.customDimValue = customDimValue;
    }
}
