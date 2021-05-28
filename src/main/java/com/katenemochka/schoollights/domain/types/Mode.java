package com.katenemochka.schoollights.domain.types;

import com.katenemochka.schoollights.domain.Room;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modes")
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String displayName;
    @OneToMany(mappedBy = "mode")
    List<Room> rooms;

    public Mode() {
    }

    public Mode(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
