package com.example.flightreservation.controller;

import com.example.flightreservation.entity.Route;
import com.example.flightreservation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RouteController {
    @Autowired
    RouteService routeService;


    @GetMapping("/route/{id}")
    public Optional<Route> findAllById(@PathVariable Integer id){
        return routeService.getRouteById(id);
    }
    @PostMapping("/routes")
    public Route save(@RequestBody Route route){
        return  routeService.saveRoute(route);
    }
}
