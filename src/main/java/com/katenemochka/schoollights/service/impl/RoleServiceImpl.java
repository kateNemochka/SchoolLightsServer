package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.RoleRepository;
import com.katenemochka.schoollights.domain.Role;
import com.katenemochka.schoollights.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No role /w id " + id)));
    }

    @Override
    @Transactional
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }

    @Override
    public Role createOrUpdate(Role role) {

        if (role.getId() != null) {

            Optional<Role> roleOptional = roleRepository.findById(role.getId());

            if (roleOptional.isPresent()) {
                Role newRole = roleOptional.get();
                newRole.setName(role.getName());
                return roleRepository.save(newRole);
            }
        }

        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No role exist for given id");
        }
    }

    public void loadDefaultData() {
        List<String> defaultRoles = new LinkedList<>();
        defaultRoles.add("ROLE_ADMIN");
        defaultRoles.add("ROLE_USER");

        for (String roleName : defaultRoles) {
            Role roleEntity = roleRepository.findByName(roleName);
            if (roleEntity == null)
                this.createOrUpdate(new Role(roleName));
        }
    }
}
