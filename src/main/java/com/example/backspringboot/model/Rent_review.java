package com.example.backspringboot.model;

import com.example.backspringboot.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent_review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private int hygiene_score;
    @Column(nullable = false)
    private int facilities_score;
    @Column(nullable = false)
    private int accessible_score;
    @Column(nullable = false)
    private double total_score;
    private String comment;
}
