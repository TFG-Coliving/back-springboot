package com.example.backspringboot.repository;

import com.example.backspringboot.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
