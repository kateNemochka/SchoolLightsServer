package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.service.ModeService;
import com.katenemochka.schoollights.service.PeriodService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class ModeController {

    ModeService modeService;
    PeriodService periodService;

    @Autowired
    public void setModeService(ModeService modeService) {
        this.modeService = modeService;
    }

    @Autowired
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping("/modes")
    public String getPredefinedModes(Model model) {
        model.addAttribute("modes", modeService.getAll());
        model.addAttribute("periods", periodService.getAll());
        return "lists/modes-list";
    }
}
