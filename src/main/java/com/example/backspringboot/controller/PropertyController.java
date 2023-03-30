package com.example.backspringboot.controller;

import com.example.backspringboot.model.ImageData;
import com.example.backspringboot.model.Property;
import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.service.ImageDataService;
import com.example.backspringboot.service.PropertyService;
import com.example.backspringboot.user.User;
import com.example.backspringboot.user.UserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;
    private final ImageDataService imageService;

    @GetMapping
    public List<Property> listAllProperties() {
        return propertyService.listAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable("id") Long id) {
        Optional<Property> optional = propertyService.getPropertyById(id);

        return optional.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property, @RequestParam("images") Optional<MultipartFile[]> files) {
        try {
            User currentUser = userService.getCurrentUser();
            property.setOwner(currentUser);
            Collection<ImageData> imageDataList = new ArrayList<>();
            if(files.isPresent()) {
                for(MultipartFile file : files.get()) {
                    ImageData imageData = imageService.uploadImage(file);
                    imageDataList.add(imageData);
                }
            }
            property.setImages(imageDataList);
            System.out.println(property);
            propertyService.saveProperty(property);
            return ResponseEntity.ok(property);
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@RequestBody Property property, @PathVariable("id") Long id) {
        try {
            User currentUser = userService.getCurrentUser();
            Property existringProperty = propertyService.getPropertyById(id).get();

            if(!existringProperty.getOwner().equals(currentUser)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            property.setId(id);
            propertyService.saveProperty(property);
            return ResponseEntity.ok(property);
        } catch (Exception ignore) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<Rent_review> addReview(@RequestBody Rent_review review, @PathVariable("id") Long id) {
        Optional<Property> optional = propertyService.getPropertyById(id);
        if(optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Property property = optional.get();
        User user = userService.getCurrentUser();
        review.setUser(user);
        property.getRentReviews().add(review);
        propertyService.saveProperty(property);
        return ResponseEntity.ok(review);
    }
}
