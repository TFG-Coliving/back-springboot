package com.example.backspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private boolean is_bid;
    @Column(nullable = false)
    private String dimensions;
    @Column(nullable = false)
    private int capacity;
    @OneToMany
    @JoinColumn(name = "room_id")
    private Collection<Bid> bids;
}
