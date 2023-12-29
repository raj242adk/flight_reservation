package com.example.flightreservation.service;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    public Booking createBooking(Booking request);
    public void confirmBooking(Integer bookingId, String enteredOTP);

    List<Booking> findAll();

    public Optional<Booking> findBookingById(Integer id);


}
