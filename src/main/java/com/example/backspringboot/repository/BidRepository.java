package com.example.backspringboot.repository;

import com.example.backspringboot.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
