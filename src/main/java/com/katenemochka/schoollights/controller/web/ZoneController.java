package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Row;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.domain.types.ZoneType;
import com.katenemochka.schoollights.dto.ZoneTypeDto;
import com.katenemochka.schoollights.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    RoomService roomService;
    DeviceService deviceService;
    DeviceTypeService deviceTypeService;
    RowService rowService;

    @Autowired
    public ZoneController(ZoneService zoneService,
                          ZoneTypeService zoneTypeService,
                          PeriodService periodService,
                          RoomService roomService,
                          DeviceService deviceService,
                          DeviceTypeService deviceTypeService,
                          RowService rowService) {
        this.zoneService = zoneService;
        this.zoneTypeService = zoneTypeService;
        this.periodService = periodService;
        this.roomService = roomService;
        this.deviceService = deviceService;
        this.deviceTypeService = deviceTypeService;
        this.rowService = rowService;
    }

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

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
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

    @GetMapping("/rooms/{roomId}/zones/{id}")
    public String getZoneById(Model model, @PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        model.addAttribute("zone_types", zoneTypeService.getAll());
        model.addAttribute("zone", zone);
        model.addAttribute("operation", "update");
        return "forms/zone-form";
    }

    @GetMapping("/rooms/{roomId}/zones/new")
    public String createZoneAtRoom(Model model, @PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        Zone zone = new Zone(room);
        model.addAttribute("zone_types", zoneTypeService.getAll());
        model.addAttribute("zone", zone);
        model.addAttribute("operation", "new");
        return "forms/zone-form";
    }

    @PostMapping({"/rooms/{roomId}/zones/{zoneId}", "/rooms/{roomId}/zones/new"})
    public String saveZone(@PathVariable(value = "zoneId", required = false) Long zoneId,
                           @PathVariable(value = "roomId") Long roomId,
                           @ModelAttribute Zone zone,
                           BindingResult result) {
        if (result.hasErrors())
            return "forms/zone-form";
        if (zone != null) {
            zoneService.createOrUpdate(zone);
        }
        return "redirect:/rooms/" + roomId;
    }

    @GetMapping("/rooms/{roomId}/zones/{id}/delete")
    public String deleteZoneById(@PathVariable Long id, @PathVariable String roomId) {
        zoneService.deleteZoneById(id);
        return "redirect:/rooms/" + roomId;
    }

    @GetMapping("/rooms/{roomId}/zones/{zoneId}/rows/add")
    public String addRowToZone(@PathVariable Long roomId,
                                    @PathVariable Long zoneId) {
        Zone zone = zoneService.getZoneById(zoneId);
        Row newRow = rowService.createDefaultRow();
        newRow.setRowNumberFromWindow(zone.getRows().size() + 1);
        rowService.addRowToZone(newRow, zone);
        return "redirect:/rooms/" + roomId;
    }

    @GetMapping("/rooms/{roomId}/zones/{zoneId}/rows/remove")
    public String removeRowFromZone(@PathVariable Long roomId,
                               @PathVariable Long zoneId) {
        Zone zone = zoneService.getZoneById(zoneId);
        System.out.println(zone);
        Long rowId = zone.getRows().stream().mapToLong(Row::getId).max().orElse(0L);
        System.out.println(rowId);
        rowService.deleteRowById(rowId);
        return "redirect:/rooms/" + roomId;
    }

}
