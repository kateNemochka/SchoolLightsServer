package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    List<Role> getAll();
    Role createOrUpdate(Role role);
    void deleteRoleById(Long id);
    void loadDefaultData();
}
