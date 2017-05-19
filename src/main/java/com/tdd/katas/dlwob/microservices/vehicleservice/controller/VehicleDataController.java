package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.VehicleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VehicleDataController.URL_MAPPING)
public class VehicleDataController {

    public static final String URL_MAPPING = "/vehicles-data";

    @Autowired
    private VehicleDataService vehicleDataService;

    @GetMapping("/{vinCode}")
    public ResponseEntity<VehicleData> getVehicleDataByVinCode(String vinCode) {
        try {
            vehicleDataService.getVehicleData(vinCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<VehicleData>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
