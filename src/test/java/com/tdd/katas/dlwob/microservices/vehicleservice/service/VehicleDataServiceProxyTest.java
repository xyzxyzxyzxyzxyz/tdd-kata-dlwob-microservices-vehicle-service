package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.model.VehicleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(VehicleDataServiceProxy.class)
public class VehicleDataServiceProxyTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private VehicleDataServiceProxy vehicleDataServiceProxy;

    @Value("${services.vehicles-data.baseUrl}")
    private String serviceBaseUrl;


    @Test
    public void Returns_null_when_vin_does_not_exist() {

        String NON_EXISTING_VIN = "NON_EXISTING_VIN";

        // Prepare server response
        server
            .expect(
                once(),
                requestTo(serviceBaseUrl + "/" + NON_EXISTING_VIN)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withStatus(HttpStatus.NOT_FOUND)
            )
        ;

        // Execute action
        VehicleData customerData = vehicleDataServiceProxy.getVehicleData(NON_EXISTING_VIN);

        // Assert expected output
        assertNull(customerData);

        // Verify expected interaction with server
        server.verify();

    }

    @Test
    public void Throws_exception_when_client_error() {

        String ANY_VIN = "ANY_VIN";

        // Prepare server response
        server
            .expect(
                once(),
                requestTo(serviceBaseUrl + "/" + ANY_VIN)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withStatus(HttpStatus.BAD_REQUEST)
            )
        ;

        try {
            // Execute action
            vehicleDataServiceProxy.getVehicleData(ANY_VIN);

            fail("Should have thrown an exception");
        }
        catch (HttpClientErrorException e) {
            // Ok
        }

        // Verify expected interaction with server
        server.verify();

    }

    @Test
    public void Throws_exception_when_server_error() {

        String ANY_VIN = "ANY_VIN";

        // Prepare server response
        server
            .expect(
                once(),
                requestTo(serviceBaseUrl + "/" + ANY_VIN)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            )
        ;

        try {
            // Execute action
            vehicleDataServiceProxy.getVehicleData(ANY_VIN);

            fail("Should have thrown an exception");
        }
        catch (HttpServerErrorException e) {
            // Ok
        }

        // Verify expected interaction with server
        server.verify();

    }


    @Test
    public void Returns_valid_data_for_existing_vin() {

        final String EXISTING_VIN = "ANY_VIN";

        // Prepare server response

        String mockJsonResponse =
                        "	{														" +
                        "		\"modelId\": \"sample-vehicle-model-id\",           " +
                        "		\"plateNumber\": \"sample-vehicle-plate-number\"    " +
                        "	}                                                       ";

        server
            .expect(
                once(),
                requestTo(serviceBaseUrl + "/" + EXISTING_VIN)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withSuccess(
                        mockJsonResponse,
                        MediaType.APPLICATION_JSON
                )
            )
        ;

        // Execute action
        VehicleData vehicleData = vehicleDataServiceProxy.getVehicleData(EXISTING_VIN);

        // Verify output
        assertNotNull(vehicleData);
        assertEquals("sample-vehicle-model-id", vehicleData.getModelId());
        assertEquals("sample-vehicle-plate-number", vehicleData.getPlateNumber());

        // Verify expected interaction with server
        server.verify();

    }

}