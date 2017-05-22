package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CompositeVehicleServiceImpl.class)
public class CompositeVehicleServiceImplTest {

    @MockBean
    private VehicleCustomerDataService vehicleCustomerDataService;

    @Autowired
    private CompositeVehicleServiceImpl compositeVehicleService;

    @Test
    public void Returns_null_when_vin_unknown_for_VehicleCustomerDataService() {

        final String UNKNOWN_VIN = "UNKNOWN_VIN";

        // Prepare collaborators
        given(
                vehicleCustomerDataService.getVehicleCustomerData(UNKNOWN_VIN)
            ).willReturn(null);

        // Perform action
        VehicleInformation actual = compositeVehicleService.getVehicleInformation(UNKNOWN_VIN);

        // Verify output
        assertNull(actual);

        // Verify interactions
        verify(vehicleCustomerDataService).getVehicleCustomerData(UNKNOWN_VIN);

    }

}
