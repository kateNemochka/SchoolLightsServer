package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.service.PeriodService;
import com.katenemochka.schoollights.service.ScheduleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Data
public class ScheduleController {

    ScheduleService scheduleService;
    PeriodService periodService;

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }


}
