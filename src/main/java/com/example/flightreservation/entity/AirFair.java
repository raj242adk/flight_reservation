package com.example.flightreservation.entity;

import jakarta.persistence.*;

@Entity
public class AirFair {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer af_Id;
     @OneToOne
     @JoinColumn(name = "route_id",referencedColumnName = "rt_id")
    private Route route;

    @Column(nullable = false)
    private Long fare;
    @Column(nullable = false)
    private Long fcs;
}
