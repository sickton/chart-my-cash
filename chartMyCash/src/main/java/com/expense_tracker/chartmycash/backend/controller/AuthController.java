package com.expense_tracker.chartmycash.backend.controller;

import com.expense_tracker.chartmycash.backend.model.User;
import com.expense_tracker.chartmycash.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        return userService.register(
                body.get("username"),
                body.get("email"),
                body.get("password")
        );
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        Optional<User> user = userService.login(
                body.get("email"),
                body.get("password")
        );

        if (user.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        return user.get();
    }
}
