package ru.kata.spring.boot.security.jpa.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot.security.jpa.entity.User;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/userAdmin")
    public String getUserAdmin(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "userAdmin";
    }
}