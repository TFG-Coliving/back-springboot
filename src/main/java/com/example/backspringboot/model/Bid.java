package com.example.backspringboot.model;

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
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date start_date;
    private Date end_date;
}
