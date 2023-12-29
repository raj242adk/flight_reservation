package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.AirCraft;
import com.example.flightreservation.repository.AirCraftRepo;
import com.example.flightreservation.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftServiceImplementation implements AircraftService {
    @Autowired
    private AirCraftRepo airCraftRepo;
    @Override
    public List<AirCraft> findAll() {
        return airCraftRepo.findAll();
    }

    @Override
    public Optional<AirCraft> getElementById(Integer id) {
        return airCraftRepo.findById(id);
    }

    @Override
    public AirCraft saveAircraft(AirCraft airCraft) {
        return airCraftRepo.save(airCraft);
    }

    @Override
    public void deleteElementById(Integer id) {
        airCraftRepo.deleteById(id);
    }


}
