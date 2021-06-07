package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class RoomController {

    @GetMapping("/room")
    public String getRoom(Model model) {
        return "room";
    }

    @GetMapping("/period")
    public String getPeriod(Model model) {
        return "/forms/periods-form";
    }
}
