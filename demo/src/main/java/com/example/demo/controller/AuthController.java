package com.example.demo.controller;

import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        boolean success = authService.login(email, password);
        if(success) {
            return "Đăng nhập thành công";
        } else {
            return "Sai email hoặc mật khẩu";
        }
    }
}
