package com.falsecolor.boot.service;

import com.falsecolor.boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(Long id);

    List<User> getUsers();

    User getUserByUsername(String username);

    User saveUser(User user);

    User saveUser(User user, String[] roles);

    void deleteUser(Long id);
}
