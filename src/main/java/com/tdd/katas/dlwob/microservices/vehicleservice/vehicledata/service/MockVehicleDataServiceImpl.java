package com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.model.VehicleData;
import org.springframework.stereotype.Service;

@Service
public class MockVehicleDataServiceImpl
        extends AbstractMockServiceImpl<VehicleData>
        implements VehicleDataService
{

    public MockVehicleDataServiceImpl() {
        super(VehicleData.class);
    }

    @Override
    public VehicleData getVehicleData(String vinCode) {
        if (MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE.equals(vinCode)) {
            return dataObject;
        }
        else {
            return null;
        }
    }

}
