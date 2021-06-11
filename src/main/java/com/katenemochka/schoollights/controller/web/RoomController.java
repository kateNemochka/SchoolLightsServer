package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.service.MicrocontrollerService;
import com.katenemochka.schoollights.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Data
@AllArgsConstructor
public class RoomController {

    RoomService roomService;
    MicrocontrollerService microcontrollerService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setMicrocontrollerService(MicrocontrollerService microcontrollerService) {
        this.microcontrollerService = microcontrollerService;
    }


    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "lists/rooms-list";
    }

    @GetMapping("/rooms/floor-{floorNum}")
    public String getAllRoomsByFloor(Model model, @PathVariable int floorNum) {
        model.addAttribute("rooms", roomService.getRoomsByFloor(floorNum));
        return "lists/rooms-list";
    }

    @GetMapping("/rooms/new")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("operation", "new");
        model.addAttribute("microcontrollers",
                microcontrollerService.getFreeMicrocontrollers());
        return "forms/room-form";
    }

    @PostMapping({"/rooms/new", "/rooms/{id}/update"})
    public String saveNewRoom(@ModelAttribute("room") Room room,
                              @PathVariable Long id,
                              BindingResult result) {
        if (result.hasErrors())
            return "forms/room-form";
        if (room != null) {
            roomService.createOrUpdate(room);
            if (room.getMicrocontroller() != null) {
                Microcontroller micro = room.getMicrocontroller();
                Microcontroller optionalMicro = microcontrollerService.getMicrocontrollerById(micro.getId());
                if (optionalMicro != null) {
                    microcontrollerService.addMicrocontrollerToRoom(optionalMicro, room);
                }
            }
        }
        return "redirect:/rooms" + (id != null ? "/" + id : "");
    }

    @GetMapping("/rooms/{id}")
    public String roomDetails(Model model, @PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room-info";
    }

    @GetMapping("/rooms/{id}/update")
    public String updateRoom(Model model, @PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        model.addAttribute("operation", "update");
        List<Microcontroller> micros = microcontrollerService.getFreeMicrocontrollers();
        if (room.getMicrocontroller() != null) {
            micros.add(room.getMicrocontroller());
        }
        model.addAttribute("microcontrollers", micros);
        return "forms/room-form";
    }

    @GetMapping("/rooms/{id}/delete")
    public String deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return "redirect:/rooms";
    }

    @GetMapping("/room-sample")
    public String getRoomSample(Model model) {
        return "room";
    }
}
