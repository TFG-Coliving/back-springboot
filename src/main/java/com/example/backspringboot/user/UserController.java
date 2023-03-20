package com.example.backspringboot.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<User> getUser() {
        try {
            User currentUser = service.getCurrentUser();
            return ResponseEntity.ok(currentUser);
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/role")
    public ResponseEntity<String> getRole() {
        try {
            return ResponseEntity.ok(service
                    .getCurrentUser()
                    .getRole()
                    .toString()
            );
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/role")
    public ResponseEntity<?> setRole(@RequestBody Role role) {
        try {
            User currentUser = service.getCurrentUser();
            currentUser.setRole(role);
            service.saveUser(currentUser);
            return ResponseEntity.ok(role);
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
