package ru.kata.spring.boot.security.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot.security.jpa.entity.User;
import ru.kata.spring.boot.security.jpa.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin")
    public String showAllUser(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUs", allUsers);
        return "admin";
    }

    @GetMapping("user-create")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("user-create")
    public String saveUser(@ModelAttribute("user") User user,  @RequestParam(value = "roles") String[] role) {
        userService.saveUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PutMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}