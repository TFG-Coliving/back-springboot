package com.example.backspringboot.repository;

import com.example.backspringboot.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
