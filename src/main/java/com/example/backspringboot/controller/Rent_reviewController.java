package com.example.backspringboot.controller;

import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.service.Rent_reviewService;
import com.example.backspringboot.user.User;
import com.example.backspringboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/review")
@RequiredArgsConstructor
public class Rent_reviewController {

    private final UserService userService;
    private final Rent_reviewService reviewService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Rent_review>> listAllReviewsOfUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if(optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Rent_review> reviews = reviewService.listAllReviewsOfUser(user);
        return ResponseEntity.ok(reviews);
    }
}
