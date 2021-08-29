package com.falsecolor.boot.controller;

import com.falsecolor.boot.model.User;
import com.falsecolor.boot.service.RoleService;
import com.falsecolor.boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        /*Set<Role> roles;
        for (int i = 1; i <= 20; i++) {
            roles = new HashSet<>();
            roles.add(roleService.getOrCreateRole("ROLE_USER"));
            userService.saveUser(new User(
                    "FirstName" + i,
                    "LastName" + i,
                    (byte) (20 + i),
                    "email" + i + "@mail.com",
                    "PASSWORD" + i,
                    roles
            ));
        }
        roles = new HashSet<>();
        roles.add(roleService.getOrCreateRole("ROLE_USER"));
        roles.add(roleService.getOrCreateRole("ROLE_ADMIN"));
        User admin = new User(
                "AFirstName",
                "ALastName",
                (byte) 100,
                "admin@mail.com",
                "ADMIN",
                roles
        );
        userService.saveUser(admin);*/
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/admin/")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PatchMapping("/admin/")
    public String patchUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}