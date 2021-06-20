package com.katenemochka.schoollights.controller.web;

import com.katenemochka.schoollights.domain.Device;
import com.katenemochka.schoollights.dto.DeviceDto;
import com.katenemochka.schoollights.service.DeviceService;
import com.katenemochka.schoollights.service.DeviceTypeService;
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
        model.addAttribute("devices", devices);
        return "lists/devices-list";
    }

    @GetMapping("/devices/{id}")
    public String getDeviceById(Model model, @PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        model.addAttribute("device", device);
        //TODO: create form for device
        return "forms/device-form";
    }

    @PostMapping("/devices/{id}")
    public String updateDeviceById(@PathVariable Long id,
                                   @ModelAttribute Device device,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "forms/device-form";
        }
        if (device != null && id.equals(device.getId())) {
            deviceService.createOrUpdate(device);
        }
        return "redirect:/devices";
    }
}
