package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Microcontroller;
import com.katenemochka.schoollights.service.MicrocontrollerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Data
public class MicroController {

    MicrocontrollerService microcontrollerService;

    @Autowired
    public void setMicrocontrollerService(MicrocontrollerService service) {
        this.microcontrollerService = service;
    }

    @GetMapping("/microcontrollers")
    public String getAllMicrocontrollers(Model model) {
        model.addAttribute("microcontrollers", microcontrollerService.getAll());
        return "lists/microcontrollers-list";
    }

    @GetMapping("/microcontrollers/createUpdate")
    public String createOrUpdateMicrocontroller(Model model) {
        model.addAttribute("microcontroller", new Microcontroller());
        return "forms/microcontroller-form";
    }

    @PostMapping("/microcontrollers/createUpdate")
    public String saveMicrocontroller(@ModelAttribute("microcontroller") Microcontroller microcontroller,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "forms/microcontroller-form";
        }
        System.out.println(microcontroller);
        if (microcontroller != null)
            microcontrollerService.createOrUpdate(microcontroller);
        return "redirect:/microcontrollers";
    }

    @GetMapping("/microcontrollers/{id}")
    public String getMicrocontrollerById(Model model, @PathVariable Long id) {
        model.addAttribute("microcontroller", microcontrollerService.getMicrocontrollerById(id));
        return "forms/microcontroller-form";
    }

    @GetMapping("/microcontrollers/{id}/delete")
    public String deleteMicrocontrollerById(@PathVariable Long id) {
        microcontrollerService.deleteMicrocontrollerById(id);
        return "redirect:/microcontrollers";
    }
}
