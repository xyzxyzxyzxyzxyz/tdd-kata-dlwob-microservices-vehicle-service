package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;

public interface VehicleDataService {
    VehicleData getVehicleData(String vinCode);
}
