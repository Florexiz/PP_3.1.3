package com.falsecolor.boot.service;

import com.falsecolor.boot.model.Role;

import java.util.Set;

public interface RoleService {
    Role getOrCreateRole(String name);

    Set<Role> getRoles();
}
