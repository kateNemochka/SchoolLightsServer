package com.katenemochka.schoollights.dto;

import com.katenemochka.schoollights.domain.Room;
import lombok.Data;


@Data
public class RoomDto {
    private Long id;
    private String name;
    private int floor;
    private String purpose;
    private String description;
    private boolean innerRoom;
    private Long microcontrollerId;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.purpose = room.getPurpose();
        this.description = room.getDescription();
        this.innerRoom = room.isInnerRoom();
        if (room.getMicrocontroller() != null) {
            this.microcontrollerId = room.getMicrocontroller().getId();
        }
    }

    public Room getRoomFromDTO() {
        Room room = new Room();
        room.setName(this.name);
        room.setFloor(this.floor);
        room.setPurpose(this.purpose);
        room.setDescription(this.description);
        room.setInnerRoom(this.innerRoom);
        return room;
    }
}
