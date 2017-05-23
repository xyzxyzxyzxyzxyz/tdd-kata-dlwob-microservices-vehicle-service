package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
class PartDataServiceProxy {

    private RestTemplate restTemplate;
    private String baseUrl;

    public PartDataServiceProxy(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${services.parts-data.baseUrl}") String baseUrl)
    {
        restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public List<PartData> getPartDataList(String vinCode) {

        ResponseEntity<PartData[]> response;
        try {
            response = restTemplate.getForEntity(baseUrl + "/" + vinCode, PartData[].class);
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            else {
                throw e;
            }
        }

        PartData[] partDataArray = response.getBody();
        return Arrays.asList(partDataArray);

    }

}
