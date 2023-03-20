package com.example.backspringboot.model;

import com.example.backspringboot.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private Date booking_date;
    private Date checkin_date;
    private Date checkout_date;
}
