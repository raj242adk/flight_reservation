package com.example.flightreservation.controller;

import com.example.flightreservation.dto.response.BookingResponse;
import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.entity.Passenger;
import com.example.flightreservation.repository.FlightRepo;
import com.example.flightreservation.repository.PassengerRepository;
import com.example.flightreservation.service.BookingService;
import com.example.flightreservation.service.OTPService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@ResponseBody
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    FlightRepo flightRepo;

    @Autowired
    OTPService otpService;

    @Autowired
    PassengerRepository passengerRepository;

    @GetMapping("/findAll")
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/save")
    public String Save() {
        return "Ok";
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBooking(@RequestBody Booking request) {
        try {
            // Call the service method to create the booking and get the provisional booking details
            Booking provisionalBooking = bookingService.createBooking(request);

            // Include the provisional booking details in the response
            return ResponseEntity.ok().body(provisionalBooking);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<Object> confirmBooking(@RequestParam Integer bookingId, @RequestParam String enteredOtp) {
        try {
            // Call the service method to confirm the booking with the entered OTP
            bookingService.confirmBooking(bookingId, enteredOtp);

            return ResponseEntity.ok().body("Booking confirmed successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Booking not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
