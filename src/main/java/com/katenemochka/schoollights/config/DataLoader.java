package com.katenemochka.schoollights.config;

import com.katenemochka.schoollights.dao.DeviceTypeRepository;
import com.katenemochka.schoollights.dao.ModeRepository;
import com.katenemochka.schoollights.dao.PeriodRepository;
import com.katenemochka.schoollights.domain.User;
import com.katenemochka.schoollights.domain.types.Mode;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    DeviceTypeService deviceTypeService;
    ModeService modeService;
    PeriodService periodService;
    RoleService roleService;
    UserService userService;

    @Autowired
    public DataLoader(DeviceTypeService deviceTypeService, ModeService modeService,
                      PeriodService periodService, RoleService roleService, UserService userService) {
        this.deviceTypeService = deviceTypeService;
        this.modeService = modeService;
        this.periodService = periodService;
        this.roleService = roleService;
        this.userService = userService;
    }

    public void run(ApplicationArguments args) {
        deviceTypeService.loadDefaultData();
        modeService.loadDefaultData();
        periodService.loadDefaultData();
        roleService.loadDefaultData();

        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("testadminuser");
        user.setFirstName("user");
        user.setLastName("surname");
        user.setRole(roleService.getRoleByName("ROLE_ADMIN"));

        User u = userService.getUserByEmail(user.getEmail());
        if (u != null) {
            user.setId(u.getId());
            userService.createOrUpdateUser(user);
        }
        userService.createOrUpdateUser(user);
    }
}
