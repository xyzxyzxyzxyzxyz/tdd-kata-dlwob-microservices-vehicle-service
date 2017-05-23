package com.tdd.katas.dlwob.microservices.vehicleservice.partdata.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.service.PartDataService;
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
        List<PartData> partDataList = null;
        try {
            partDataList = partDataService.getPartsByVinCode(vinCode);
        }
        catch (Exception e) {
            return new ResponseEntity<List<PartData>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (partDataList == null) {
            return new ResponseEntity<List<PartData>>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<List<PartData>>(partDataList, HttpStatus.OK);
        }
    }

}
