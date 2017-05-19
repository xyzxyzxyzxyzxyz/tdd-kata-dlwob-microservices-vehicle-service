package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VehicleController.URL_MAPPING)
public class VehicleController {

    public static final String URL_MAPPING = "/vehicles";


    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{vinCode}")
    public ResponseEntity<VehicleInformation> getVehicle(String vinCode) {
        VehicleInformation vinfo;

        try {
            vinfo = vehicleService.getVehicleInformation(vinCode);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (vinfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vinfo, HttpStatus.OK);
        }
    }

}
