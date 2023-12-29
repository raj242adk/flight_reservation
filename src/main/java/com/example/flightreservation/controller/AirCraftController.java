package com.example.flightreservation.controller;

import com.example.flightreservation.entity.AirCraft;
import com.example.flightreservation.service.impl.AircraftServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AirCraftController {
    @Autowired
    private AircraftServiceImplementation aircraftServiceImplementation;

    @GetMapping("/air-craft")
    public List<AirCraft> findAll(){
       return aircraftServiceImplementation.findAll();
    }
    @PostMapping("/air-craft")
    public AirCraft save(@RequestBody AirCraft airCraft){
       return aircraftServiceImplementation.saveAircraft(airCraft);
    }
    @GetMapping("/air-craft/{id}")
    public Optional<AirCraft> findById(@PathVariable Integer id){
        return aircraftServiceImplementation.getElementById(id);
    }

    @DeleteMapping("/air-craft/{id}")
    public void delete(@PathVariable Integer id){
        aircraftServiceImplementation.deleteElementById(id);
    }

    @PutMapping("/air-craft/{id}")
    public AirCraft update(@PathVariable Integer id,@RequestBody AirCraft airCraft){
        aircraftServiceImplementation.getElementById(id);
        airCraft.setAir_id(id);
        return  aircraftServiceImplementation.saveAircraft(airCraft);
    }




}
