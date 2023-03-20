package com.example.backspringboot.repository;

import com.example.backspringboot.model.Facility;
import com.example.backspringboot.model.Property;
import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p where p.facilities = ?1")
    Collection<Property> findByFacilities(@NonNull Facility facilities);

    @Query("select p from Property p where p.rooms = ?1")
    Collection<Property> findByRooms(@NonNull Room rooms);

    @Query("select p from Property p where p.rentReviews = ?1")
    Collection<Property> findByRentReviews(@Nullable Rent_review rentReviews);
}
