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
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @PostMapping("/admin/")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "selectedRoles") String[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PutMapping("/admin/")
    public String patchUser(@ModelAttribute User user,
                            @RequestParam(value = "selectedRoles", required = false) String[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }
}