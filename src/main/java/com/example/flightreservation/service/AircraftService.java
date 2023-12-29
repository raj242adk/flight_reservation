package com.example.flightreservation.service;

import com.example.flightreservation.entity.AirCraft;

import java.util.List;
import java.util.Optional;

public interface AircraftService {

    public List<AirCraft> findAll();

    public Optional<AirCraft> getElementById(Integer id);
    public AirCraft saveAircraft(AirCraft airCraft);
    public void deleteElementById(Integer id);


}
