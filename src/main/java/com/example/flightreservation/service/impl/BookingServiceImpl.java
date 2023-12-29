package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.entity.OTPEntity;
import com.example.flightreservation.entity.Passenger;
import com.example.flightreservation.repository.BookingRepo;
import com.example.flightreservation.repository.FlightRepo;
import com.example.flightreservation.repository.OTPrepo;
import com.example.flightreservation.repository.PassengerRepository;
import com.example.flightreservation.service.BookingService;
import com.example.flightreservation.service.OTPService;
import com.example.flightreservation.service.others.EmailService;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    FlightRepo flightRepo;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    OTPrepo otpRepo;

    @Autowired
    @Lazy
    OTPService otpService;

    @Autowired
    @Lazy
    EmailService emailService;


    @Override
    @Transactional
    public Booking createBooking(Booking request) {
        Integer flightId = request.getFlight().getId();
        Integer passengerId = request.getPassenger().getId();
        Integer seatsToBook = request.getSeats();

        // Retrieve flight and passenger details
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new NoSuchElementException("Flight not found"));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new NoSuchElementException("Passenger not found"));

        // Generate OTP
        String otp = otpService.generateOTP();

        // Send OTP via Email using EmailService with template
        String recipient = request.getEmail();
        String subject = "OTP for Booking";
        String template = "Your OTP for booking is: ${otp}\n\n"
                + "Please use this OTP to complete your booking.";

        Map<String, Object> variables = new HashMap<>();
        variables.put("otp", otp);

        emailService.sendEmailWithTemplate(recipient, subject, template, variables);

        // Save the provisional booking
        Booking provisionalBooking = new Booking();
        provisionalBooking.setFlight(flight);
        provisionalBooking.setPassenger(passenger);
        provisionalBooking.setSeats(seatsToBook);
        provisionalBooking.setEmail(request.getEmail());
        provisionalBooking.setConfirmed("pending confirmation"); // Set initial status

        // Save the provisional booking entity to get the generated ID
        Booking savedProvisionalBooking = bookingRepo.save(provisionalBooking);

        // Associate OTP with the provisional booking in the database
        LocalDateTime expirationTime = otpService.getOtpExpirationTime();
        otpService.storeOtpInDatabase(savedProvisionalBooking.getId(), otp, expirationTime);

        return savedProvisionalBooking;
    }


    @Override
    @Transactional
    public void confirmBooking(Integer bookingId, String enteredOTP) {
        // Retrieve OTP associated with the booking from the database
        String storedOTP = otpService.getOtp(bookingId);

        // Validate OTP
        boolean isOTPValid = otpService.validateOtp(bookingId, enteredOTP);

        if (isOTPValid) {
            // Update booking status to "confirmed"
            Booking confirmedBooking = bookingRepo.findById(bookingId)
                    .orElseThrow(() -> new NoSuchElementException("Booking not found"));

            confirmedBooking.setConfirmed("confirmed");

            // Perform any necessary post-confirmation actions (e.g., payment processing)

            // Delete OTP from the database
            otpService.deleteOtp(bookingId);

            // Save the updated booking
            bookingRepo.save(confirmedBooking);
        } else {
            // Handle invalid OTP (throw an exception, return an error response, etc.)
            throw new IllegalArgumentException("Invalid OTP. Booking confirmation failed.");
        }
    }


    @Override
    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    @Override
    public Optional<Booking> findBookingById(Integer id) {
        return bookingRepo.findById(id);
    }




}
