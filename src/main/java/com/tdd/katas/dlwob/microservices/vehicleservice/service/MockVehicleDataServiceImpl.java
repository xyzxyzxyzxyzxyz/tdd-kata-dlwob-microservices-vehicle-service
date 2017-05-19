package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;

import java.io.IOException;
import java.io.InputStream;

public class MockVehicleDataServiceImpl implements VehicleDataService {

    public MockVehicleDataServiceImpl() {
        readJsonFile();
    }

    private VehicleData sampleVData;

    private void readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);

            VehicleData vdata = objectMapper.readValue(resourceInputStream, VehicleData.class);

            sampleVData = vdata;
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
    public VehicleData getVehicleData(String vinCode) {
        if (MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE.equals(vinCode)) {
            return sampleVData;
        }
        else {
            return null;
        }
    }

}
