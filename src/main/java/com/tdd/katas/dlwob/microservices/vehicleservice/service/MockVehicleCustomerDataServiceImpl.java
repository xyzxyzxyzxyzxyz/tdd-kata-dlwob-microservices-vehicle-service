package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import org.springframework.stereotype.Service;

@Service
public class MockVehicleCustomerDataServiceImpl
        extends AbstractMockServiceImpl<VehicleCustomerData>
        implements VehicleCustomerDataService
{

    public MockVehicleCustomerDataServiceImpl() {
        super(VehicleCustomerData.class);
    }

    @Override
    public VehicleCustomerData getVehicleCustomerData(String vinCode) {
        if (MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE.equals(vinCode)) {
            return dataObject;
        }

        return null;
    }

}
