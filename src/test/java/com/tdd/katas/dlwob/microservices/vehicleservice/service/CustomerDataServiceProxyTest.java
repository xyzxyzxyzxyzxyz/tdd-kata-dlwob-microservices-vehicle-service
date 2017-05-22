package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.CustomerDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

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

}
