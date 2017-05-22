package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompositeVehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleCustomerDataService vehicleCustomerDataService;

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {

        VehicleCustomerData vcd = vehicleCustomerDataService.getVehicleCustomerData(vinCode);
        if (vcd == null) {
            return null;
        }

        return null;

    }

}
