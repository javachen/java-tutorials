package com.javachen.springboot.drools.controller;

import com.javachen.springboot.drools.model.Fare;
import com.javachen.springboot.drools.model.TaxiRide;
import com.javachen.springboot.drools.service.TaxiFareCalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TaxiFareCalculatorService taxiFareCalculatorService;

    @GetMapping("/calculate/{distance}")
    public Long calculate(@PathVariable(name = "distance") Long distance) {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(distance);
        Fare rideFare = new Fare();
        return taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
    }
}
