package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Zone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*Спосіб керування освітленням*/
@Entity
@Table(name = "modes")
@Setter
@Getter
@NoArgsConstructor
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String displayName;
    @OneToMany(mappedBy = "mode")
    List<Zone> zones;

    public Mode(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}
