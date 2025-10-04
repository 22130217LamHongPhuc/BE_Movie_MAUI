package com.example.demo.controller;

import com.example.demo.dto.ProfileResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new ProfileResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress()

            );
        } else {
            throw new RuntimeException("User không tồn tại");
        }
    }
}
