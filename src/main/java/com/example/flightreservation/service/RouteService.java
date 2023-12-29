package com.example.flightreservation.service;

import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.entity.Route;

import java.util.Optional;

public interface RouteService {




    public Optional<Route> getRouteById(Integer id);


    public Route saveRoute(Route route);
}
