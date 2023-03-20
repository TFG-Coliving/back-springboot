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
public class User_review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_reviewed_id", nullable = false)
    private User user_reviewer;
    @OneToOne
    @JoinColumn(name = "user_valued_id", nullable = false)
    private User user_valued;
    @Column(nullable = false)
    private int total_score;
    private String comment;
}
