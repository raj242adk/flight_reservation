package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.OTPEntity;
import com.example.flightreservation.repository.OTPrepo;
import com.example.flightreservation.service.BookingService;
import com.example.flightreservation.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class OTPServiceImpl implements OTPService {

    @Autowired
    OTPrepo otpRepo;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    BookingService bookingService; // Inject BookingService to get email

    @Override
    public String generateOTP() {
        return String.format("%06d", new SecureRandom().nextInt(1000000));
    }

    @Override
    public void sendOtpByEmail(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("GO WITH ME OTP VERIFICATION");
        mailMessage.setText("Your Go With Me OTP is: " + otp + "\n Please Don't Share this OTP with Anyone");
        javaMailSender.send(mailMessage);
    }

    @Override
    public void storeOtpInDatabase(Integer bookingId, String otp, LocalDateTime expirationTime) {
        // Get the email from the BookingService
        Optional<Booking> bookingOptional = bookingService.findBookingById(bookingId);

        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();

            Optional<OTPEntity> existingOtpEntity = otpRepo.findByBookingId(bookingId);

            if (existingOtpEntity.isPresent()) {
                // Update existing OTP
                OTPEntity otpEntity = existingOtpEntity.get();
                otpEntity.setOtp(otp);
                otpEntity.setExpirationTime(expirationTime);
                otpRepo.save(otpEntity);
            } else {
                // Create a new OTP entry
                OTPEntity newOtpEntity = new OTPEntity();
                newOtpEntity.setBookingId(bookingId);
                newOtpEntity.setOtp(otp);
                newOtpEntity.setExpirationTime(expirationTime);
                otpRepo.save(newOtpEntity);
            }
        } else {
            throw new NoSuchElementException("Booking not found for ID: " + bookingId);

        }
    }


    @Override
    public boolean validateOtp(Integer bookingId, String enteredOtp) {
        Optional<OTPEntity> otpEntityOptional = otpRepo.findByBookingId(bookingId);

        if (otpEntityOptional.isPresent()) {
            OTPEntity otpEntity = otpEntityOptional.get();
            // Validate entered OTP
            return enteredOtp.equals(otpEntity.getOtp()) && LocalDateTime.now().isBefore(otpEntity.getExpirationTime());
        }

        return false;
    }

    @Override
    public LocalDateTime getOtpExpirationTime() {
        // Set the expiration time to 5 minutes from the current time
        return LocalDateTime.now().plusMinutes(5);
    }

    @Override
    public String getOtp(Integer bookingId) {
        Optional<OTPEntity> otpEntityOptional = otpRepo.findByBookingId(bookingId);
        return otpEntityOptional.map(OTPEntity::getOtp).orElse(null);
    }

    @Override
    public void deleteOtp(Integer bookingId) {
        otpRepo.deleteByBookingId(bookingId);
    }
}
