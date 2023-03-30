package com.example.backspringboot.model;

import com.example.backspringboot.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    @Column(nullable = false)
    private String country;
    private String province;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private double coordinates_long_north;
    @Column(nullable = false)
    private double coordinates_latitude_east;
    @Column(nullable = false)
    private double score;
    @Column(nullable = false)
    private int available_rooms;
    @Column(nullable = false)
    private String dimensions;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id")
    private Collection<Facility> facilities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", nullable = false)
    private Collection<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_reviews_id")
    private Collection<Rent_review> rentReviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_data_id")
    private Collection<ImageData> images;
}
