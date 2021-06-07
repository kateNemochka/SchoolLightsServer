package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.service.DeviceService;
import com.katenemochka.schoollights.service.DeviceTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Data
public class DeviceController {

    private DeviceService deviceService;
    private DeviceTypeService deviceTypeService;

    @Autowired
    public void setDeviceTypeService(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices/device-types")
    public String getAllDeviceTypes(Model model) {
        model.addAttribute("deviceTypes", deviceTypeService.getAll());
        return "device-types-list";
    }
}
