package com.example.flightreservation.repository;

import com.example.flightreservation.entity.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCraftRepo extends JpaRepository<AirCraft,Integer> {
}
