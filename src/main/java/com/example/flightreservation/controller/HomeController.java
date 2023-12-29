package com.example.flightreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@ResponseBody
public class HomeController {

    @GetMapping("/home")
    public String homepage(){
        return "HomePage.html";
    }
}
