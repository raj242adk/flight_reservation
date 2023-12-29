package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {


    boolean existsByPassengerId(Integer id);
    Optional<Booking> findById(Integer id);
}
