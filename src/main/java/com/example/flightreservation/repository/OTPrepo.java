package com.example.flightreservation.repository;

import com.example.flightreservation.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPrepo extends JpaRepository<OTPEntity,Integer> {
    Optional<OTPEntity> findByBookingId(Integer bookingId);

    void deleteByBookingId(Integer bookingId);
}
