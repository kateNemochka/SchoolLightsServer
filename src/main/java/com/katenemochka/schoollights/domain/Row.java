package com.katenemochka.schoollights.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="lights_row")
@Setter
@Getter
public class Row {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int rowNumberFromWindow;
    private int customDimValue;
    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;

    public Row() {
    }
}
