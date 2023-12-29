package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.repository.FlightRepo;
import com.example.flightreservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepo flightRepo;

    @Override
    public List<Flight> findAll() {
        return flightRepo.findAll();
    }


    @Override
    public Optional<Flight> findFlightById(Integer id) {
        return flightRepo.findById(id);
    }

    @Override
    public Flight updateFlight(Integer id, Flight updateflight) {
        Optional<Flight> flight = flightRepo.findById(updateflight.getId());
        if (flight.isPresent()) {
            // Get the existing flight from the optional
            Flight existingFlight = flight.get();
            existingFlight.setFlightNumber(updateflight.getFlightNumber());
            existingFlight.setAirline(updateflight.getAirline());
            existingFlight.setDeparture_gate(updateflight.getDeparture_gate());
            existingFlight.setArrival_gate(updateflight.getArrival_gate());
            existingFlight.setDepartureDateTime(updateflight.getDepartureDateTime());
            existingFlight.setArrivalDateTime(updateflight.getArrivalDateTime());
            existingFlight.setAvailableSeats(updateflight.getAvailableSeats());
            return flightRepo.save(existingFlight);
        } else {
            return null;
        }

    }


    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public void deleteElementById(Integer id) {
        flightRepo.deleteById(id);
    }


    @Override
    public void validateDepartureTime(Flight flight) {
        if (flight.getDepartureDateTime() != null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime departureDateTime = toLocalDateTime(flight.getDepartureDateTime());

            if (departureDateTime.isBefore(currentDateTime)) {
                throw new IllegalArgumentException("Departure time must not be in the past");
            }
        }



    }

    @Override
    public LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


}
