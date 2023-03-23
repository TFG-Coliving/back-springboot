package com.example.backspringboot.user;

import com.example.backspringboot.model.ImageData;
import com.example.backspringboot.service.ImageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ImageDataService imageService;

    @GetMapping
    public ResponseEntity<User> getUser() {
        try {
            User currentUser = userService.getCurrentUser();
            return ResponseEntity.ok(currentUser);
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/role")
    public ResponseEntity<String> getRole() {
        try {
            return ResponseEntity.ok(userService
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
            User currentUser = userService.getCurrentUser();
            currentUser.setRole(role);
            userService.saveUser(currentUser);
            return ResponseEntity.ok(role);
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/image")
    public ResponseEntity<ImageData> changeImage(@RequestParam("image")MultipartFile file) {
        try {
            User user = userService.getCurrentUser();
            ImageData image = user.getProfilePicture();
            ImageData newImage = imageService.updateImage(file, image);

            return ResponseEntity.ok(newImage);
        } catch (IOException ignore) {
            return ResponseEntity.badRequest().build();
        }
    }
}
