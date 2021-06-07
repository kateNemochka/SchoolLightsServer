package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.service.ModeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class ModeController {

    ModeService modeService;

    @Autowired
    public void setModeService(ModeService modeService) {
        this.modeService = modeService;
    }

    @GetMapping("/modes")
    public String getAllDeviceTypes(Model model) {
        model.addAttribute("modes", modeService.getAll());
        return "lists/modes-list";
    }

}
