package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.PartDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
class PartDataServiceProxy {

    private RestTemplate restTemplate;

    public PartDataServiceProxy(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public List<PartData> getPartDataList(String vinCode) {
        try {
            restTemplate.getForEntity(PartDataController.URL_MAPPING + "/" + vinCode, PartData[].class);
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            else {
                throw e;
            }
        }

        throw new UnsupportedOperationException("Not implemented");
    }

}
