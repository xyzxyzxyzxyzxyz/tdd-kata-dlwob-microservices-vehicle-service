package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PartDataController.URL_MAPPING)
public class PartDataController {

    public static final String URL_MAPPING = "/parts-data";

    @GetMapping("/{vinCode}")
    public void getPartsByVinCode(@PathVariable String vinCode) {
        return;
    }

}
