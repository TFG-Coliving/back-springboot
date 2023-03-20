package com.example.backspringboot.repository;

import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Rent_reviewRepository extends JpaRepository<Rent_review, Long> {

    List<Rent_review> findByUser(User user);
}
