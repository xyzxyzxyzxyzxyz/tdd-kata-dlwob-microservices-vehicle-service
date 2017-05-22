package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class CustomerDataServiceProxy {

    private RestTemplate restTemplate;

    public CustomerDataServiceProxy(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public CustomerData getCustomerData(String non_existing_customer_id) {
        return null;
    }

}
