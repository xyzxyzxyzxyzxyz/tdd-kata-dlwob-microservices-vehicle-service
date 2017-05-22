package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;

public interface VehicleCustomerDataService {

    VehicleCustomerData getVehicleCustomerData(String vinCode);

}
