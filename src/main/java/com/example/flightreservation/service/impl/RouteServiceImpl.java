package com.example.flightreservation.service.impl;

import com.example.flightreservation.entity.Route;
import com.example.flightreservation.repository.RouteRepo;
import com.example.flightreservation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
     public RouteRepo routeRepo;
    @Override
    public Optional<Route> getRouteById(Integer id)
    {
        return routeRepo.findById(id);
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepo.save(route);

    }
}
