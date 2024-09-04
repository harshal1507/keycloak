package com.poc.sso.client.controller;


import com.poc.sso.client.model.Response;
import com.poc.sso.client.model.User;
import com.poc.sso.client.service.UserService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/create-user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @GetMapping("/result")
    public String showResultPage(Model model) {
        return "result";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        Response response = userService.createUser(user);
        model.addAttribute("message", "User created successfully: " + user.getUsername());
        return "result";
    }

    @GetMapping("/token")
    @ResponseBody
    public ResponseEntity<AccessTokenResponse> generateToken(){
        AccessTokenResponse accessTokenResponse = userService.generateToken();
        return ResponseEntity.ok(accessTokenResponse);
    }

    @PostMapping("/save-user")
    @ResponseBody
    public ResponseEntity<Response> createUser(@RequestBody User user){
        Response response = userService.createUser(user);
        return ResponseEntity.ok(response);
    }



}
