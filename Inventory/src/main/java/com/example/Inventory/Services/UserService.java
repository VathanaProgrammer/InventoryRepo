package com.example.Inventory.Services;

import com.example.Inventory.Models.User;
import com.example.Inventory.Repositories.UserRepository;
import com.example.Inventory.Request.UserRequest;
import com.example.Inventory.Responses.ResponseUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // No security config needed
    }

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");


    public Map<String, Object> createUser(UserRequest request) {

        if (request.getEmail() == null || !EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            return ResponseUtil.error("Invalid email");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);

        return ResponseUtil.success("User created successfully", user);
    }

    public Map<String, Object> login(UserRequest request) {
        if (request == null) {
            return ResponseUtil.error("User request is null");
        }

        // 1️⃣ Check if email exists
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseUtil.error("Email does not exist");
        }

        User user = userOpt.get();

        // 2️⃣ Compare passwords
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseUtil.error("Invalid password");
        }

        // 3️⃣ Success
        return ResponseUtil.success("Login successful", user);
    }

}
