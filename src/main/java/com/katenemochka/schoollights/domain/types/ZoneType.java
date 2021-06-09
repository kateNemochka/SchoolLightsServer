package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Zone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "zone_types")
@Setter
@Getter
@ToString
public class ZoneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int lightMinimum;
    private int lightMaximum;
    /*@ElementCollection
    @CollectionTable(name="detection_intervals",
            joinColumns=@JoinColumn(name="zone_type"))
    @Column(name="time_interval")
        @MapKeyJoinColumn(name="period", referencedColumnName="id")*/
    @ElementCollection
    @MapKeyJoinColumn(name="period_id")
    Map<Period, Integer> detectionIntervals;
    @ElementCollection
    @MapKeyJoinColumn(name="period_id")
    /*@ElementCollection
    @CollectionTable(name="light_timeouts",
            joinColumns=@JoinColumn(name="zone_type"))
    @Column(name="time_interval")
    @MapKeyJoinColumn(name="period", referencedColumnName="id")*/
    Map<Period, Integer> lightTimeouts;
    @OneToMany(mappedBy = "zoneType")
    List<Zone> zones;


    public ZoneType() {
    }

    public List<Integer> getDetectionIntervalsSorted() {
        return detectionIntervals.keySet()
                .stream()
                .sorted(Period::compareTo)
                .mapToInt(p -> detectionIntervals.get(p))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<Integer> getLightTimeoutsSorted() {
        return lightTimeouts.keySet()
                .stream()
                .sorted(Period::compareTo)
                .mapToInt(p -> lightTimeouts.get(p))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
