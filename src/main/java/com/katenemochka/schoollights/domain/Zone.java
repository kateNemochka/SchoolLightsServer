package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.types.ZoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
@Setter
@Getter
public class Zone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
    private String name;
    @ManyToOne
    @JoinColumn(name="zone_type_id")
    private ZoneType zoneType;
    @OneToMany(mappedBy = "zone")
    private List<Device> deviceList;
    @OneToMany(mappedBy = "zone")
    private List<Row> rowList;
    @ManyToOne
    @JoinColumn(name="mode_id")
    private Mode mode;
    private int modeTimeout;
    private int dimmerValue;


    public Zone() {
    }
}
