package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockPartDataServiceImpl implements PartDataService
{

    @Override
    public List<PartData> getPartsByVinCode(String non_existent_vin) {
        return null;
    }

}
