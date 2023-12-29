package com.example.flightreservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer air_id;
    private String air_Number;
    private Integer capacity;
    private String mfd_by;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mfd_on;
}
