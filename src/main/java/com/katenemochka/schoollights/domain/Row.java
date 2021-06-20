package com.katenemochka.schoollights.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lights_row")
@Setter
@Getter
public class Row {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int rowNumberFromWindow;
    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;
    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> devices = new ArrayList<>();

    public Row() {
    }

    public Row(int rowNumberFromWindow) {
        this.rowNumberFromWindow = rowNumberFromWindow;
    }

    public Row(Zone zone, int rowNumberFromWindow) {
        this.zone = zone;
        this.rowNumberFromWindow = rowNumberFromWindow;
    }
}
