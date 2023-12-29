package com.example.flightreservation.dto.request;

import lombok.Data;

@Data
public class BookingRequestDTO {

    private String firstName;
    private String lastName;
    private Integer flightId;
}
