package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.domain.types.ZoneType;
import com.katenemochka.schoollights.dto.ZoneTypeDto;
import com.katenemochka.schoollights.service.PeriodService;
import com.katenemochka.schoollights.service.ZoneService;
import com.katenemochka.schoollights.service.ZoneTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@Data
public class ZoneController {

    ZoneService zoneService;
    ZoneTypeService zoneTypeService;
    PeriodService periodService;

    @Autowired
    public void setZoneService(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @Autowired
    public void setZoneTypeService(ZoneTypeService zoneTypeService) {
        this.zoneTypeService = zoneTypeService;
    }

    @Autowired
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping("/zones/zone-types")
    public String getAllZoneTypes(Model model) {
        model.addAttribute("zoneTypes", zoneTypeService.getAll());
        return "lists/zone-types-list";
    }

    @GetMapping("/zones/zone-types/createUpdate")
    public String newZoneType(Model model) {
        ZoneTypeDto dto = new ZoneTypeDto();
        Map<String, Period> periodByNames = periodService.getAll().stream()
                .collect(Collectors.toMap(Period::getName, Function.identity()));
        System.out.println(periodByNames);
        model.addAttribute("zoneTypeDto", dto.getDtoFromZoneType(new ZoneType(), periodByNames));
        model.addAttribute("periodDisplayNames", periodService.getAll().stream()
                .collect(Collectors.toMap(Period::getName, Period::getDisplayName)));
        return "forms/zone-type-form";
    }

    @GetMapping("/zones/zone-types/{id}")
    public String getZoneTypeById(Model model, @PathVariable Long id) {
        ZoneTypeDto dto = new ZoneTypeDto();
        model.addAttribute("zoneTypeDto",
                dto.getDtoFromZoneType(zoneTypeService.getZoneTypeById(id)));
        model.addAttribute("periodDisplayNames", periodService.getAll().stream()
                .collect(Collectors.toMap(Period::getName, Period::getDisplayName)));
        return "forms/zone-type-form";
    }

    @PostMapping("/zones/zone-types/createUpdate")
    public String createOrUpdateZoneType(@ModelAttribute("zoneTypeDto") ZoneTypeDto zoneTypeDto,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "forms/zone-type-form";
        }
        System.out.println(zoneTypeDto);
        Map<String, Period> periodByNames = periodService.getAll().stream()
                .collect(Collectors.toMap(Period::getName, Function.identity()));
        if (zoneTypeDto != null)
            zoneTypeService.createOrUpdate(zoneTypeDto.convertZoneTypeDtoToZoneType(periodByNames));
        return "redirect:/zones/zone-types";
    }

    @GetMapping("/zones/zone-types/{id}/delete")
    public String deleteZoneTypeById(@PathVariable Long id) {
        zoneTypeService.deleteZoneTypeById(id);
        return "redirect:/zones/zone-types";
    }

    @GetMapping("/zones")
    public String getAllZones(Model model) {
        List<Zone> zones = zoneService.getAll();
        model.addAttribute("zones", zones);
        return "lists/zones-list";
    }

    @GetMapping("/zones/{id}")
    public String getZoneById(Model model, @PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        model.addAttribute("zone", zone);
        return "forms/zone-form";
    }
}
