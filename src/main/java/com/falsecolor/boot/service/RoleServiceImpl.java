package com.falsecolor.boot.service;

import com.falsecolor.boot.model.Role;
import com.falsecolor.boot.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrCreateRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
