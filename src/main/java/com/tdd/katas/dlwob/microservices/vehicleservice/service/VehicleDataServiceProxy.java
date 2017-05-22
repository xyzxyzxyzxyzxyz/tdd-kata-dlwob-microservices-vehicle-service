package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.VehicleDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
class VehicleDataServiceProxy {

    private RestTemplate restTemplate;

    public VehicleDataServiceProxy(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public VehicleData getVehicleData(String vinCode) {
        try {
            restTemplate.getForEntity(
                    VehicleDataController.URL_MAPPING + "/" + vinCode,
                    VehicleData.class);
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
        }

        throw new UnsupportedOperationException("Not implemented");
    }

}