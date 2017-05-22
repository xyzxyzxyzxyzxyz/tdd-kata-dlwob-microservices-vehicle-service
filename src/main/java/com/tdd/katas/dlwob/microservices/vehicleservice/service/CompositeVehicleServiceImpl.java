package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.springframework.stereotype.Service;

@Service
public class CompositeVehicleServiceImpl implements VehicleService {

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {
        throw new UnsupportedOperationException("To be implemented");
    }

}
