package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MockVehicleServiceImplTest {

    @Test
    public void testContainsSampleData() throws UnsupportedEncodingException {
        VehicleService mockVehicleService = new MockVehicleServiceImpl();

        VehicleInformation vinfo = mockVehicleService.getVehicleInformation(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE);

        assertNotNull(vinfo);
        assertEquals(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE, vinfo.getVin());

        assertNotNull(vinfo.getVehicleData());
        assertEquals("sample-vehicle-model-id", vinfo.getVehicleData().getModelId());
        assertEquals("sample-vehicle-plate-number", vinfo.getVehicleData().getPlateNumber());

        assertNotNull(vinfo.getCustomerData());
        assertEquals("sample-customer-id", vinfo.getCustomerData().getId());
        assertEquals("Sergio", vinfo.getCustomerData().getName());
        assertEquals("Osuna Medina", vinfo.getCustomerData().getSurnames());



        assertNotNull(vinfo.getPartsList());
        assertEquals(3, vinfo.getPartsList().size());

        assertNotNull(vinfo.getPartsList().get(0));
        assertEquals("sample-part-1-id", vinfo.getPartsList().get(0).getId());
        assertEquals("sample-part-1-description", vinfo.getPartsList().get(0).getDescription());

        assertNotNull(vinfo.getPartsList().get(1));
        assertEquals("sample-part-2-id", vinfo.getPartsList().get(1).getId());
        assertEquals("sample-part-2-description", vinfo.getPartsList().get(1).getDescription());

        assertNotNull(vinfo.getPartsList().get(2));
        assertEquals("sample-part-3-id", vinfo.getPartsList().get(2).getId());
        assertEquals("sample-part-3-description", vinfo.getPartsList().get(2).getDescription());
    }

}
