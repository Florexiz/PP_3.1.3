package com.falsecolor.boot.service;

import com.falsecolor.boot.model.Role;

public interface RoleService {
    Role getOrCreateRole(String name);
}
