package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.DayOff;
import com.katenemochka.schoollights.domain.ScheduleEvent;
import com.katenemochka.schoollights.domain.DayOffType;
import com.katenemochka.schoollights.service.DayOffService;
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
    DayOffService dayOffService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,
                              PeriodService periodService,
                              DayOffService dayOffService) {
        this.scheduleService = scheduleService;
        this.periodService = periodService;
        this.dayOffService = dayOffService;
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
    public String deleteScheduleEvent(@PathVariable Long eventId) {
        ScheduleEvent event = scheduleService.getScheduleEventById(eventId);
        if (event != null && eventId.equals(event.getId()))
            scheduleService.deleteScheduleEventById(eventId);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/daysOff")
    public String getDaysOff(Model model) {
        model.addAttribute("daysOff", dayOffService.getAll().stream().sorted().collect(Collectors.toList()));
        return "lists/days-off-list";
    }

    @GetMapping("/schedule/daysOff/new")
    public String createNewDayOff(Model model) {
        DayOff dayOff = new DayOff();
        model.addAttribute("dayOff", dayOff);
        model.addAttribute("operation", "new");
        return "forms/day-off-form";
    }

    @GetMapping("/schedule/daysOff/{dayOffId}")
    public String updateDayOff(@PathVariable Long dayOffId,
                               Model model) {
        model.addAttribute("dayOff", dayOffService.getDayOffById(dayOffId));
        model.addAttribute("operation", "update");
        return "forms/day-off-form";
    }

    @PostMapping({"/schedule/daysOff/new", "/schedule/daysOff/{dayOffId}"})
    public String saveDayOff(@PathVariable(required = false) Long dayOffId,
                             @ModelAttribute DayOff dayOff,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "forms/day-off-form";
        }
        if (dayOff != null && dayOff.getType().equals(DayOffType.SINGLE_DAY)) {
            dayOff.setEndDate(dayOff.getStartDate());
        }
        dayOffService.createOrUpdate(dayOff);
        return "redirect:/schedule/daysOff";
    }

    @GetMapping("/schedule/daysOff/{dayOffId}/delete")
    public String deleteDayOff(@PathVariable Long dayOffId) {
        DayOff dayOff = dayOffService.getDayOffById(dayOffId);
        if (dayOff != null && dayOffId.equals(dayOff.getId()))
            dayOffService.deleteDayOffById(dayOffId);
        return "redirect:/schedule/daysOff";
    }
}
