package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PartDataController.URL_MAPPING)
public class PartDataController {

    public static final String URL_MAPPING = "/parts-data";

    @Autowired
    private PartDataService partDataService;

    @GetMapping("/{vinCode}")
    public ResponseEntity<List<PartData>> getPartsByVinCode(@PathVariable String vinCode) {
        partDataService.getPartsByVinCode(vinCode);
        return new ResponseEntity<List<PartData>>(HttpStatus.NOT_FOUND);
    }

}
