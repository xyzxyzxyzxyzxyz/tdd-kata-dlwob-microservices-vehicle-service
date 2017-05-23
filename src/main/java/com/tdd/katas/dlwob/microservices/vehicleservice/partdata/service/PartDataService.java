package com.tdd.katas.dlwob.microservices.vehicleservice.partdata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;

import java.util.List;

public interface PartDataService {
    List<PartData> getPartsByVinCode(String non_existent_vin);
}
