package com.example.backspringboot.service;

import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.repository.Rent_reviewRepository;
import com.example.backspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Rent_reviewService {

    @Autowired
    private Rent_reviewRepository reviewRepository;

    public List<Rent_review> listAllReviewsOfUser(User user) {
        return reviewRepository.findByUser(user);
    }
}
