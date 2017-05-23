package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
class CustomerDataServiceProxy {

    private RestTemplate restTemplate;

    private String baseUrl;

    public CustomerDataServiceProxy(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${services.customers-data.baseUrl}") String baseUrl)
    {
        restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public CustomerData getCustomerData(String nonExistingCustomerId)
            throws
                HttpClientErrorException,
                HttpServerErrorException
    {
        try {
            ResponseEntity<CustomerData> customerData = restTemplate.getForEntity(baseUrl + "/" + nonExistingCustomerId, CustomerData.class);
            return customerData.getBody();
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            else {
                throw e;
            }
        }
    }

}
