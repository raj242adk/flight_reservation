package com.example.flightreservation.controller;

import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.service.FlightService;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FlightController {
    @Autowired
    FlightService flightService;


    @GetMapping("/flight/findAll")
    public String flightFindAll(Model model) {
        List<Flight> flight = flightService.findAll();
        model.addAttribute("allFlight", flight);
        return "showAllFlight";

    }

    @GetMapping("/flight/add")
    public String flight() {
        return "FlightDetails";
    }

    @PostMapping("/flight/add")
    public String flightadd(@Valid @ModelAttribute Flight flight, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "FlightDetails";
        }

        if (flight.getDepartureDateTime().before(new Date())) {
            result.rejectValue("departureDateTime", "error.flight", "Departure date must be in the future");
            model.addAttribute("errorTitle", "Invalid Departure Date");
            model.addAttribute("errorMessage", "Departure date must be in the future");
            return "errorPage";
        }

        // Check if arrivalDateTime is in the past
        if (flight.getArrivalDateTime().before(new Date())) {
            result.rejectValue("arrivalDateTime", "error.flight", "Arrival date must be in the future");
            model.addAttribute("errorTitle", "Invalid Arrival Date");
            model.addAttribute("errorMessage", "Arrival date must be in the future");
            return "errorPage";
        }
         flightService.saveFlight(flight);

        // If there are no validation errors, redirect to the "/showAllFlight" page
        return "admin";
    }




    @GetMapping("/flight/edit/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // Retrieve the flight with the given ID
        Optional<Flight> optionalFlight = flightService.findFlightById(id);

        // Check if the flight exists
        if (optionalFlight.isPresent()) {
            // Pass the flight to the model for the view
            model.addAttribute("flight", optionalFlight.get());

            // Return the view for editing (replace "edit-flight-form" with your actual view name)
            return "EditFlightForm";
        } else {
            // If the flight does not exist, you might handle this case differently (redirect, show an error page, etc.)
            return "redirect:/admin";
        }
    }

    // Mapping to handle the form submission and update the flight
    // Mapping to handle the form submission and update the flight
    @PostMapping("/flight/edit/{id}")
    public String updateFlight(@PathVariable Integer id, @ModelAttribute Flight updateFlight, BindingResult result, Model model) {
        // Validate departure time (not in the past)
        if (result.hasErrors()) {
            return "FlightDetails";
        }

        if (updateFlight.getDepartureDateTime().before(new Date())) {
            result.rejectValue("departureDateTime", "error.flight", "Departure date must be in the future");
            model.addAttribute("errorTitle", "Invalid Departure Date");
            model.addAttribute("errorMessage", "Departure date must be in the future");
            return "errorPage";
        }

        // Check if arrivalDateTime is in the past
        if (updateFlight.getArrivalDateTime().before(new Date())) {
            result.rejectValue("arrivalDateTime", "error.flight", "Arrival date must be in the future");
            model.addAttribute("errorTitle", "Invalid Arrival Date");
            model.addAttribute("errorMessage", "Arrival date must be in the future");
            return "errorPage";

        }

        flightService.updateFlight(id,updateFlight);
        return "redirect:/admin";

    }
    @GetMapping("/flight/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        flightService.deleteElementById(id);
        return "redirect:/admin";

    }
}
