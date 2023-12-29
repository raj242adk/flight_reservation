package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Integer> {

}
