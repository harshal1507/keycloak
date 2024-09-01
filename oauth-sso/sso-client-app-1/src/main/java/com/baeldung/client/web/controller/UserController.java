package com.baeldung.client.web.controller;

import com.baeldung.client.customuser.CustomService;
import com.baeldung.client.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private CustomService customService;

    @GetMapping("/create-user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        try {
            customService.createUser(user);
            model.addAttribute("message", "User created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to create user: " + e.getMessage());
        }

        return "result";
    }
}