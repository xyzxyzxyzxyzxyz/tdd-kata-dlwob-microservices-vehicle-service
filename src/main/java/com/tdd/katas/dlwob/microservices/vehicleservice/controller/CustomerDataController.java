package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerDataController.URL_MAPPING)
public class CustomerDataController {

    public static final String URL_MAPPING = "/customers-data";

    @GetMapping("/{customerId}")
    public void getCustomerDataByCustomerId() {
        return;
    }

}
