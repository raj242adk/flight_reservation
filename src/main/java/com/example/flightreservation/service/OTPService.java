package com.example.flightreservation.service;

import java.time.LocalDateTime;

public interface OTPService {
    String generateOTP();
    void sendOtpByEmail(String email, String otp);
    void storeOtpInDatabase(Integer bookingId, String otp, LocalDateTime expirationTime);
    boolean validateOtp(Integer bookingId, String enteredOtp);
    LocalDateTime getOtpExpirationTime();

    String getOtp(Integer bookingId);
    void deleteOtp(Integer bookingId);

}
