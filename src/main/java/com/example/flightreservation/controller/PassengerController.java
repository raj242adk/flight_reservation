package com.example.flightreservation.controller;

import com.example.flightreservation.entity.Passenger;
import com.example.flightreservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @GetMapping("/passenger/all")
    public String showAllPassengers(Model model) {
        List<Passenger> allPassengers = passengerService.getAllPassenger();
        model.addAttribute("passengers", allPassengers);
        return "passenger/Passenger"; // Create a Thymeleaf template to display the data
    }


    @GetMapping("/passenger/details")
    public String addPassenger(){
        return "passenger/AddPassenger";
    }

    @PostMapping("/passenger/details")
    public String savePassenger(@ModelAttribute Passenger passenger) {
        passengerService.savePassenger(passenger);
        return "redirect:/passenger/Passenger";
    }



}
