package com.falsecolor.boot.controller;

import com.falsecolor.boot.model.Role;
import com.falsecolor.boot.model.User;
import com.falsecolor.boot.service.RoleService;
import com.falsecolor.boot.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class MainRestController {

    private final UserService userService;
    private final RoleService roleService;

    public MainRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/api/user")
    public User getCurrentUser(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @GetMapping("/api/admin/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/admin/roles")
    public Set<Role> getAllRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/api/admin/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/api/admin/")
    public User saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "selectedRoles", required = false) String[] roles) {
        return userService.saveUser(user, roles);
    }

    @DeleteMapping("/api/admin/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/api/admin/")
    public User editUser(@ModelAttribute User user,
                         @RequestParam(value = "selectedRoles", required = false) String[] roles) {
        return userService.saveUser(user, roles);
    }
}
