package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
class VehicleDataServiceProxy {

    public VehicleDataServiceProxy(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.build();
    }

    public VehicleData getVehicleData(String vinCode) {
        return null;
    }

}
