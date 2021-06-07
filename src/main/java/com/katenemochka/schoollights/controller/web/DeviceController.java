package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.dto.DeviceDto;
import com.katenemochka.schoollights.service.DeviceService;
import com.katenemochka.schoollights.service.DeviceTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


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
        return "lists/device-types-list";
    }

    @GetMapping("/devices")
    public String getAllDevices(Model model) {
        List<Device> devices = deviceService.getAll();
        List<DeviceDto> deviceDTOs = devices.stream()
                .map(DeviceDto::new)
                .collect(Collectors.toList());
        model.addAttribute("devices", deviceDTOs);
        return "lists/devices-list";
    }

    @GetMapping("/devices/{id}")
    public String getDeviceById(Model model, @PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        model.addAttribute("device", device);
        //TODO: create form for device
        return "forms/device-form";
    }
}
