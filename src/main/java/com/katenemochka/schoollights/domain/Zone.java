package com.katenemochka.schoollights.domain;

import com.katenemochka.schoollights.domain.types.ModeTimestamp;
import com.katenemochka.schoollights.domain.types.ZoneType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "zones")
@Setter
@Getter
@NoArgsConstructor
public class Zone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="room_id", nullable=true)
    private Room room;
    @ManyToOne
    @JoinColumn(name="zone_type_id", nullable = true)
    private ZoneType zoneType;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> devices = new ArrayList<>();
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Row> rows = new ArrayList<>();
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModeTimestamp> modesData = new ArrayList<>();

    public Zone(Room room) {
        this.room = room;
    }
}
