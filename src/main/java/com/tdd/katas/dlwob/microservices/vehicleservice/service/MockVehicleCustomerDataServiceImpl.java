package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;

public class MockVehicleCustomerDataServiceImpl
        extends AbstractMockServiceImpl<VehicleCustomerData>
        implements VehicleCustomerDataService
{

    public MockVehicleCustomerDataServiceImpl() {
        super(VehicleCustomerData.class);
    }

    @Override
    public VehicleCustomerData getVehicleCustomerData(String vinCode) {
        return dataObject;
    }

}