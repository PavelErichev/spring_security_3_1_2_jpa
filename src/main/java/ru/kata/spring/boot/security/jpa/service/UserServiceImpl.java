package ru.kata.spring.boot.security.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot.security.jpa.entity.Role;
import ru.kata.spring.boot.security.jpa.entity.User;
import ru.kata.spring.boot.security.jpa.repository.RoleRepository;
import ru.kata.spring.boot.security.jpa.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
        /*Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());*/
    }

    @Transactional
    @Override
    public void saveUser(User user, String[] role) {
        user.setPassword(PasswordEncoder().encode(user.getPassword()));
        Set<Role> rolesSet = new HashSet<>();
        for (String roles : role) {
            rolesSet.add(roleRepository.getByName(roles));
        }
        user.setRoles(rolesSet);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public boolean deleteUser(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /*@Transactional
    @Override
    public void updateUser(User user, String[] role) {
        user.setPassword(PasswordEncoder().encode(user.getPassword()));
        Set<Role> rolesSet = new HashSet<>();
        for (String roles : role) {
            rolesSet.add(roleRepository.getByName(roles));
        }
        user.setRoles(rolesSet);
        userRepository.save(user);
    }*/

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}