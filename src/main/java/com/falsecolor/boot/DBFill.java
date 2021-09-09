package com.falsecolor.boot;

import com.falsecolor.boot.model.User;
import com.falsecolor.boot.service.RoleService;
import com.falsecolor.boot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DBFill {

    private final UserService userService;
    private final RoleService roleService;

    public DBFill(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void generateUsers() {
        for (int i = 1; i <= 5; i++) {
            userService.saveUser(new User(
                    "FirstName" + i,
                    "LastName" + i,
                    (byte) (10 + i),
                    "email" + i + "@mail.com",
                    "PASSWORD" + i,
                    new HashSet<>(Collections.singletonList(
                            roleService.getOrCreateRole("ROLE_USER")
                    ))
            ));
        }

        userService.saveUser(new User(
                "AFirstName",
                "ALastName",
                (byte) 123,
                "admin@mail.com",
                "ADMIN",
                new HashSet<>(Arrays.asList(
                        roleService.getOrCreateRole("ROLE_USER"),
                        roleService.getOrCreateRole("ROLE_ADMIN")
                ))
        ));
    }
}