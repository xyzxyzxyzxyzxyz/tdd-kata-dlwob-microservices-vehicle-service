package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MockVehicleCustomerServiceImplTest {

    @Test
    public void Returns_null_if_vin_does_not_exist() {
        VehicleCustomerDataService mockVehicleCustomerDataService = new MockVehicleCustomerDataServiceImpl();

        final String NON_EXISTING_VIN = "NON_EXISTING_VIN";
        VehicleCustomerData vcd = mockVehicleCustomerDataService.getVehicleCustomerData(NON_EXISTING_VIN);

        assertNull(vcd);
    }

}
