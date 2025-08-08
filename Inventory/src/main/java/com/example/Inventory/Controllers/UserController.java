package com.example.Inventory.Controllers;

import com.example.Inventory.Request.UserRequest;
import com.example.Inventory.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        Map<String, Object> res = userService.createUser(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        Map<String, Object> res = userService.login(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }
}
