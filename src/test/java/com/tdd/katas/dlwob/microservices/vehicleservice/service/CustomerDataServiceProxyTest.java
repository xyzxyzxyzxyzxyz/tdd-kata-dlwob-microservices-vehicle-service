package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.CustomerDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestClientTest(CustomerDataServiceProxy.class)
public class CustomerDataServiceProxyTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private CustomerDataServiceProxy customerDataServiceProxy;

    @Test
    public void Returns_null_when_customer_does_not_exist() {

        String NON_EXISTING_CUSTOMER_ID = "NON_EXISTING_CUSTOMER_ID";

        // Prepare server response
        server
            .expect(
                once(),
                requestTo(CustomerDataController.URL_MAPPING + "/" + NON_EXISTING_CUSTOMER_ID)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withStatus(HttpStatus.NOT_FOUND)
            )
        ;

        // Execute action
        CustomerData customerData = customerDataServiceProxy.getCustomerData(NON_EXISTING_CUSTOMER_ID);

        // Assert expected output
        assertNull(customerData);

        // Verify expected interaction with server
        server.verify();

    }

    @Test
    public void Throws_exception_when_client_error() {

        String ANY_CUSTOMER_ID = "ANY_CUSTOMER_ID";

        // Prepare server response
        server
                .expect(
                        once(),
                        requestTo(CustomerDataController.URL_MAPPING + "/" + ANY_CUSTOMER_ID)
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.BAD_REQUEST)
                )
        ;


        try {
            // Execute action
            customerDataServiceProxy.getCustomerData(ANY_CUSTOMER_ID);

            fail("Should have thrown an exception");
        }
        catch (HttpClientErrorException e) {
            // Ok
        }

        // Verify expected interaction with server
        server.verify();
    }

    @Test
    public void Throws_exception_when_service_error() {

        String ANY_CUSTOMER_ID = "ANY_CUSTOMER_ID";

        // Prepare server response
        server
                .expect(
                        once(),
                        requestTo(CustomerDataController.URL_MAPPING + "/" + ANY_CUSTOMER_ID)
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                )
        ;


        try {
            // Execute action
            customerDataServiceProxy.getCustomerData(ANY_CUSTOMER_ID);

            fail("Should have thrown an exception");
        }
        catch (HttpServerErrorException e) {
            // Ok
        }

        // Verify expected interaction with server
        server.verify();
    }

    @Test
    public void Returns_valid_data_for_existing_customer() {

        final String EXISTING_CUSTOMER_ID = "sample-customer-id";

        // Prepare server response

        String mockJsonResponse =
                        "	{										" +
                        "		\"id\": \"sample-customer-id\",     " +
                        "		\"name\": \"Sergio\",               " +
                        "		\"surnames\": \"Osuna Medina\"      " +
                        "	}                                       ";

        server
            .expect(
                once(),
                requestTo(CustomerDataController.URL_MAPPING + "/" + EXISTING_CUSTOMER_ID)
            )
            .andExpect(method(HttpMethod.GET))
            .andRespond(
                withSuccess(
                    mockJsonResponse,
                    MediaType.APPLICATION_JSON)
            )
        ;

        // Execute action
        CustomerData customerData = customerDataServiceProxy.getCustomerData(EXISTING_CUSTOMER_ID);

        // Verify output
        assertNotNull(customerData);
        assertEquals(EXISTING_CUSTOMER_ID, customerData.getId());
        assertEquals("Sergio", customerData.getName());
        assertEquals("Osuna Medina", customerData.getSurnames());

        // Verify expected interaction with server
        server.verify();
    }

}
