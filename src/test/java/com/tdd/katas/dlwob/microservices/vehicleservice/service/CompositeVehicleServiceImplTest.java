package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CompositeVehicleServiceImpl.class)
public class CompositeVehicleServiceImplTest {

    @MockBean
    private VehicleCustomerDataService vehicleCustomerDataService;
    @MockBean
    private CustomerDataServiceProxy customerDataServiceProxy;
    @MockBean
    private VehicleDataServiceProxy vehicleDataServiceProxy;
    @MockBean
    private PartDataServiceProxy partDataServiceProxy;

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



    private static final String ANY_VIN = "ANY_VIN";
    private static final String ANY_CUSTOMER_ID = "ANY_CUSTOMER_ID";

    @Test
    public void Throws_exception_when_sub_service_error() {

        // VehicleCustomerDataService

            willThrowException(vehicleCustomerDataService);

            willReturnValidData(customerDataServiceProxy);
            willReturnValidData(vehicleDataServiceProxy);
            willReturnValidData(partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException();

        // CustomerDataServiceProxy
            willThrowException(customerDataServiceProxy);

            willReturnValidData(vehicleCustomerDataService);
            willReturnValidData(vehicleDataServiceProxy);
            willReturnValidData(partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException();
        // VehicleDataServiceProxy
            willThrowException(vehicleDataServiceProxy);

            willReturnValidData(vehicleCustomerDataService);
            willReturnValidData(customerDataServiceProxy);
            willReturnValidData(partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException();

        // PartDataServiceProxy
            willThrowException(partDataServiceProxy);

            willReturnValidData(vehicleCustomerDataService);
            willReturnValidData(customerDataServiceProxy);
            willReturnValidData(vehicleDataServiceProxy);

            assertCompositeVehicleServiceThrowsException();
    }

    private void assertCompositeVehicleServiceThrowsException() {
        try {
            // Perform action
            compositeVehicleService.getVehicleInformation(ANY_VIN);

            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            // Ok
            return;
        }

    }

    private void willReturnValidData(Object serviceOrProxy) {
        // Reset mock
        Mockito.reset(serviceOrProxy);

        // Prepare behaviour

        if (serviceOrProxy == vehicleCustomerDataService) {
            VehicleCustomerData vcd = new VehicleCustomerData();
            vcd.setCustomerId(ANY_CUSTOMER_ID);

            given(
                vehicleCustomerDataService.getVehicleCustomerData(ANY_VIN)
            ).willReturn(vcd);
        }
        else if (serviceOrProxy == customerDataServiceProxy) {
            CustomerData customerData = new CustomerData();

            given(
                customerDataServiceProxy.getCustomerData(ANY_CUSTOMER_ID)
            ).willReturn(customerData);
        }
        else if (serviceOrProxy == vehicleDataServiceProxy) {
            VehicleData vehicleData = new VehicleData();

            given(
                vehicleDataServiceProxy.getVehicleData(ANY_VIN)
            ).willReturn(vehicleData);
        }
        else if (serviceOrProxy == partDataServiceProxy) {
            List<PartData> partDataList = new ArrayList<>();

            given(
                partDataServiceProxy.getPartDataList(ANY_VIN)
            ).willReturn(partDataList);
        }
        else {
            throw new UnsupportedOperationException("Unreachable code");
        }

    }

    private void willThrowException(Object serviceOrProxy) {
        // Reset mock
        Mockito.reset(serviceOrProxy);

        // Prepare behaviour

        if (serviceOrProxy == vehicleCustomerDataService) {
            given(
                vehicleCustomerDataService.getVehicleCustomerData(ANY_VIN)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == customerDataServiceProxy) {
            given(
                customerDataServiceProxy.getCustomerData(ANY_CUSTOMER_ID)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == vehicleDataServiceProxy) {
            given(
                vehicleDataServiceProxy.getVehicleData(ANY_VIN)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == partDataServiceProxy) {
            given(
                partDataServiceProxy.getPartDataList(ANY_VIN)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else {
            throw new UnsupportedOperationException("Unreachable code");
        }

    }


}
