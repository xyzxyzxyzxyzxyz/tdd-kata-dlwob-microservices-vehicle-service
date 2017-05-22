package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;

import java.util.List;

public interface PartDataService {
    List<PartData> getPartsByVinCode(String non_existent_vin);
}
