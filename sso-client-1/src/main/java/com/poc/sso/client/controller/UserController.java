package com.poc.sso.client.controller;


import com.poc.sso.client.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/create-user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        // Here you would call the service to create the user
        // For simplicity, let's assume the user creation is always successful
        model.addAttribute("message", "User created successfully: " + user.getUsername());
        return "result";
    }
}
