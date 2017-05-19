package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VehicleDataController.URL_MAPPING)
public class VehicleDataController {

    public static final String URL_MAPPING = "/vehicles-data";

    @GetMapping("/{vinCode}")
    public void getVehicleDataByVinCode(String vinCode) {
    }

}
