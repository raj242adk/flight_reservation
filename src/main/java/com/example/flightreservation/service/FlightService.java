package com.example.flightreservation.service;

import com.example.flightreservation.entity.Flight;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {

     public List<Flight> findAll();

     public Optional< Flight> findFlightById(Integer id);


     public Flight updateFlight(Integer id, Flight updateflight);

     Flight saveFlight(Flight flight);

      void deleteElementById(Integer id);

      void validateDepartureTime(Flight flight);

     LocalDateTime toLocalDateTime(Date date);


}
