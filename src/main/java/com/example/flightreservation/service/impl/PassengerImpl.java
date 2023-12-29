package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.Passenger;
import com.example.flightreservation.repository.PassengerRepository;
import com.example.flightreservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PassengerImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;
    @Override
    public List<Passenger> getAllPassenger() {
       return passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> getPassengerById(Integer id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);

    }

    @Override
    public void deletePassengerById(Integer id) {
        passengerRepository.deleteById(id);
    }
}
