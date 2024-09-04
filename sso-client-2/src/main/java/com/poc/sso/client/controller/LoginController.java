package com.poc.sso.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";  // returns the login.html Thymeleaf template
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // Replace with actual authentication logic
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/success";  // Redirect to a success page
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            model.addAttribute("username", username);  // Preserve the username input
            return "login";  // Reload the login page with an error message
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";  // returns the success.html Thymeleaf template
    }
}
