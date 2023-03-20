package com.example.backspringboot.repository;

import com.example.backspringboot.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
}
