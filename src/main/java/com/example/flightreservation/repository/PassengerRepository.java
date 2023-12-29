package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Integer> {

    Optional<Object> findById(Passenger passenger);
}
