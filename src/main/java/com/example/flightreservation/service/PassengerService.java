package com.example.flightreservation.service;

import com.example.flightreservation.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PassengerService {

    List<Passenger> getAllPassenger();
    Optional<Passenger> getPassengerById(Integer id);

    Passenger savePassenger(Passenger passenger);

    void deletePassengerById(Integer id);


}
