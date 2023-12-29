package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepo extends JpaRepository<Route,Integer> {
}
