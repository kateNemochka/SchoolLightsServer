package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.ScheduleEvent;
import com.katenemochka.schoollights.service.PeriodService;
import com.katenemochka.schoollights.service.ScheduleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;


@Controller
@Data
public class ScheduleController {

    ScheduleService scheduleService;
    PeriodService periodService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, PeriodService periodService) {
        this.scheduleService = scheduleService;
        this.periodService = periodService;
    }

    @GetMapping("/schedule")
    public String getFullSchedule(Model model) {
        model.addAttribute("schedule", scheduleService.getAll().stream().sorted().collect(Collectors.toList()));
        return "lists/schedule-list";
    }

    @GetMapping("/schedule/{eventId}")
    public String getScheduleEventById(Model model, @PathVariable Long eventId) {
        ScheduleEvent event = scheduleService.getScheduleEventById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("operation", "update");
        model.addAttribute("periodTypes", periodService.getAll());
        return "forms/schedule-event-form";
    }

    @GetMapping("/schedule/new")
    public String createNewEvent(Model model) {
        ScheduleEvent event = new ScheduleEvent();
        model.addAttribute("event", event);
        model.addAttribute("operation", "new");
        model.addAttribute("periodTypes", periodService.getAll());
        return "forms/schedule-event-form";
    }

    @PostMapping({"/schedule/{eventId}", "/schedule/new"})
    public String updateDeviceById(@PathVariable(required = false) Long eventId,
                                   @ModelAttribute ScheduleEvent event,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "forms/schedule-event-form";
        }
        scheduleService.createOrUpdate(event);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/{eventId}/delete")
    public String deleteScheduleEvent(Model model, @PathVariable Long eventId) {
        ScheduleEvent event = scheduleService.getScheduleEventById(eventId);
        if (event != null && eventId.equals(event.getId()))
            scheduleService.deleteScheduleEventById(eventId);
        return "redirect:/schedule";
    }
}
