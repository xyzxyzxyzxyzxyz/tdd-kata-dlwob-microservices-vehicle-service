package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MockVehicleCustomerServiceImplTest {

    private VehicleCustomerDataService mockVehicleCustomerDataService;

    @Before
    public void setUp() {
        mockVehicleCustomerDataService = new MockVehicleCustomerDataServiceImpl();
    }

    @Test
    public void Returns_null_if_vin_does_not_exist() {
        final String NON_EXISTING_VIN = "NON_EXISTING_VIN";
        VehicleCustomerData vcd = mockVehicleCustomerDataService.getVehicleCustomerData(NON_EXISTING_VIN);

        assertNull(vcd);
    }

    @Test
    public void Returns_valid_data_for_existing_vin() {
        VehicleCustomerData vcd = mockVehicleCustomerDataService.getVehicleCustomerData(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE);

        assertNotNull(vcd);
        assertEquals(MockServicesConstants.SAMPLE_CUSTOMER_ID, vcd.getCustomerId());
    }

}
