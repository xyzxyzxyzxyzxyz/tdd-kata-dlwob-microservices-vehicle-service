package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;

public class MockVehicleServiceImpl implements VehicleService {

    public static final String SAMPLE_VEHICLE_VIN_CODE = "vehicle-vin-code";

    public MockVehicleServiceImpl() {
    }

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {
        return null;
    }
}
