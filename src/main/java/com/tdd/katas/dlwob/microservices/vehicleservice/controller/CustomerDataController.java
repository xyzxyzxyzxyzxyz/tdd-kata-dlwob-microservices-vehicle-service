package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerDataController.URL_MAPPING)
public class CustomerDataController {

    public static final String URL_MAPPING = "/customers-data";

    @Autowired
    private CustomerDataService customerDataService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerData> getCustomerDataByCustomerId(@PathVariable String customerId) {
        customerDataService.getCustomerData(customerId);
        return new ResponseEntity<CustomerData>(HttpStatus.NOT_FOUND);
    }

}
