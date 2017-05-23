package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.model.VehicleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
class VehicleDataServiceProxy {

    private RestTemplate restTemplate;
    private String baseUrl;

    public VehicleDataServiceProxy(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${services.vehicles-data.baseUrl}") String baseUrl)
    {
        restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public VehicleData getVehicleData(String vinCode) throws HttpClientErrorException, HttpServerErrorException {
        ResponseEntity<VehicleData> response;

        try {
            response = restTemplate.getForEntity(
                    baseUrl + "/" + vinCode,
                    VehicleData.class);
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            else {
                throw e;
            }
        }

        return response.getBody();
    }

}
