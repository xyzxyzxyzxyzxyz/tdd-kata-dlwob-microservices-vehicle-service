package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VehicleController.URL_MAPPING)
public class VehicleController {

    public static final String URL_MAPPING = "/vehicles";

    @GetMapping("/{vinCode}")
    public void getVehicle(String vinCode) {
    }

}
