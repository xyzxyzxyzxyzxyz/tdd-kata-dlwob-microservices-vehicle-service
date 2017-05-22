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

    @Test
    public void Throws_exception_when_sub_service_error() {

        final String ANY_VIN = "ANY_VIN";

        // VehicleCustomerDataService

            willThrowException(ANY_VIN, vehicleCustomerDataService);

            willReturnValidData(ANY_VIN, customerDataServiceProxy);
            willReturnValidData(ANY_VIN, vehicleDataServiceProxy);
            willReturnValidData(ANY_VIN, partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException(ANY_VIN);

        // CustomerDataServiceProxy
            willThrowException(ANY_VIN, customerDataServiceProxy);

            willReturnValidData(ANY_VIN, vehicleCustomerDataService);
            willReturnValidData(ANY_VIN, vehicleDataServiceProxy);
            willReturnValidData(ANY_VIN, partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException(ANY_VIN);
        // VehicleDataServiceProxy
            willThrowException(ANY_VIN, vehicleDataServiceProxy);

            willReturnValidData(ANY_VIN, vehicleCustomerDataService);
            willReturnValidData(ANY_VIN, customerDataServiceProxy);
            willReturnValidData(ANY_VIN, partDataServiceProxy);

            assertCompositeVehicleServiceThrowsException(ANY_VIN);

        // PartDataServiceProxy
            willThrowException(ANY_VIN, partDataServiceProxy);

            willReturnValidData(ANY_VIN, vehicleCustomerDataService);
            willReturnValidData(ANY_VIN, customerDataServiceProxy);
            willReturnValidData(ANY_VIN, vehicleDataServiceProxy);

            assertCompositeVehicleServiceThrowsException(ANY_VIN);
    }

    private void assertCompositeVehicleServiceThrowsException(String vinCode) {
        try {
            // Perform action
            compositeVehicleService.getVehicleInformation(vinCode);

            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            // Ok
            return;
        }

    }

    private void willReturnValidData(String vinCode, Object serviceOrProxy) {
        // Reset mock
        Mockito.reset(serviceOrProxy);

        // Prepare behaviour

        final String CUSTOMER_ID = "CUSTOMER_ID";

        if (serviceOrProxy == vehicleCustomerDataService) {
            VehicleCustomerData vcd = new VehicleCustomerData();
            vcd.setCustomerId(CUSTOMER_ID);

            given(
                vehicleCustomerDataService.getVehicleCustomerData(vinCode)
            ).willReturn(vcd);
        }
        else if (serviceOrProxy == customerDataServiceProxy) {
            CustomerData customerData = new CustomerData();

            given(
                customerDataServiceProxy.getCustomerData(CUSTOMER_ID)
            ).willReturn(customerData);
        }
        else if (serviceOrProxy == vehicleDataServiceProxy) {
            VehicleData vehicleData = new VehicleData();

            given(
                vehicleDataServiceProxy.getVehicleData(vinCode)
            ).willReturn(vehicleData);
        }
        else if (serviceOrProxy == partDataServiceProxy) {
            List<PartData> partDataList = new ArrayList<>();

            given(
                partDataServiceProxy.getPartDataList(vinCode)
            ).willReturn(partDataList);
        }
        else {
            throw new UnsupportedOperationException("Unreachable code");
        }

    }

    private void willThrowException(String vinCode, Object serviceOrProxy) {
        // Reset mock
        Mockito.reset(serviceOrProxy);

        // Prepare behaviour

        final String CUSTOMER_ID = "CUSTOMER_ID";

        if (serviceOrProxy == vehicleCustomerDataService) {
            given(
                vehicleCustomerDataService.getVehicleCustomerData(vinCode)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == customerDataServiceProxy) {
            given(
                customerDataServiceProxy.getCustomerData(CUSTOMER_ID)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == vehicleDataServiceProxy) {
            given(
                vehicleDataServiceProxy.getVehicleData(vinCode)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else if (serviceOrProxy == partDataServiceProxy) {
            given(
                partDataServiceProxy.getPartDataList(vinCode)
            ).willThrow(new RuntimeException("Not ready"));
        }
        else {
            throw new UnsupportedOperationException("Unreachable code");
        }

    }


}
