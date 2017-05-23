package com.tdd.katas.dlwob.microservices.vehicleservice.partdata.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockPartDataServiceImpl
        extends AbstractMockServiceImpl<List<PartData>>
        implements PartDataService
{

    public MockPartDataServiceImpl() {
        super(
            new TypeReference<List<PartData>>() {
            }
        );
    }

    @Override
    public List<PartData> getPartsByVinCode(String non_existent_vin) {
        return dataObject;
    }

}
