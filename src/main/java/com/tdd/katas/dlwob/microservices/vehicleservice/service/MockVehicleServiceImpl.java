package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;

import java.io.IOException;
import java.io.InputStream;

public class MockVehicleServiceImpl implements VehicleService {

    public static final String SAMPLE_VEHICLE_VIN_CODE = "sample-vehicle-vin-code";

    public MockVehicleServiceImpl() {
        readJsonFile();
    }

    private VehicleInformation sampleVInfo;

    private void readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);

            VehicleInformation vinfo = objectMapper.readValue(resourceInputStream, VehicleInformation.class);

            sampleVInfo = vinfo;
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error reading from JSON file", e);
        } finally {
            if (resourceInputStream!=null) {
                try {
                    resourceInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {
        if (sampleVInfo.getVin().equals(vinCode)) {
            return sampleVInfo;
        }
        else {
            return null;
        }
    }
}
