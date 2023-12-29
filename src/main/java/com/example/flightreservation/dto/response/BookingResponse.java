package com.example.flightreservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookingResponse {

    private Integer bookingId;
    private Integer flightNumber;
    private String passengerName;
    private Date bookingDate;
}
