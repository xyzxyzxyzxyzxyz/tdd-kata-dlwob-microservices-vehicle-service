package com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.model.VehicleData;

public interface VehicleDataService {
    VehicleData getVehicleData(String vinCode);
}
