package ru.kata.spring.boot.security.jpa.service;

import ru.kata.spring.boot.security.jpa.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    void saveUser(User user, String[] role);

    boolean deleteUser(long id);
}