package com.example.backspringboot.controller;

import com.example.backspringboot.model.ImageData;
import com.example.backspringboot.service.ImageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final ImageDataService imageService;

    @PostMapping("/image")
    public ResponseEntity<ImageData> uploadImage(@RequestParam("image")MultipartFile file) {
        try {
            imageService.uploadImage(file);
            return ResponseEntity.ok().build();
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok().build();
        } catch (Exception ignore) {
            return ResponseEntity.notFound().build();
        }
    }
}
