package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;

public interface VehicleService {
    VehicleInformation getVehicleInformation(String vinCode);
}
