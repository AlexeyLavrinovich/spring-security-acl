package com.aliakseila.springsecurityacl.controller;

import com.aliakseila.springsecurityacl.model.entity.User;
import com.aliakseila.springsecurityacl.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity info() {
        return ResponseEntity.ok("Set your full name and password");
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestBody User user) {
        userService.createNewUser(user);
        return ResponseEntity.ok("Registration successfully finished!");
    }

}
