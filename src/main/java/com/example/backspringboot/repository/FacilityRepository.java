package com.example.backspringboot.repository;

import com.example.backspringboot.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
