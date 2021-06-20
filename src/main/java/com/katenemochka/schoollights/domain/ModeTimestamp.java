package com.katenemochka.schoollights.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "zone_modes")
@Data
public class ModeTimestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mode_id")
    private Mode mode;
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    private LocalDateTime timestamp;
    private Integer modeTimeout;

    public ModeTimestamp() {}

    public ModeTimestamp(Mode mode,  Zone zone, Integer modeTimeout) {
        this.mode = mode;
        this.zone = zone;
        this.modeTimeout = modeTimeout;
    }
}
