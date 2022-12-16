package ru.kata.spring.boot.security.jpa.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot.security.jpa.entity.Role;
import ru.kata.spring.boot.security.jpa.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUser(long id);

    void saveUser(User user, String[] role);

    boolean deleteUser(long id);

    void updateUser(User user);

    Role findRoleById(long id);
}