package com.falsecolor.boot.service;

import com.falsecolor.boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(Long id);

    List<User> getUsers();

    void saveUser(User user);

    void deleteUser(Long id);
}
