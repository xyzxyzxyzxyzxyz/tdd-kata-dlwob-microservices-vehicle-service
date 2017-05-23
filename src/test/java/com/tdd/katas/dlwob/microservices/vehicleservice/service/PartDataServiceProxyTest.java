package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
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

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(PartDataServiceProxy.class)
public class PartDataServiceProxyTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private PartDataServiceProxy partDataServiceProxy;

    @Value("${services.parts-data.baseUrl}")
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
        List<PartData> partDataList = partDataServiceProxy.getPartDataList(NON_EXISTING_VIN);

        // Assert expected output
        assertNull(partDataList);

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

        // Execute action
        try {
            partDataServiceProxy.getPartDataList(ANY_VIN);

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

        // Execute action
        try {
            partDataServiceProxy.getPartDataList(ANY_VIN);

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

        String ANY_VIN = "ANY_VIN";

        // Prepare server response

        String mockServerResponse =
                "	[															" +
                "		{                                                       " +
                "			\"id\": \"sample-part-1-id\",                       " +
                "			\"description\": \"sample-part-1-description\"      " +
                "		},                                                      " +
                "		{                                                       " +
                "			\"id\": \"sample-part-2-id\",                       " +
                "			\"description\": \"sample-part-2-description\"      " +
                "		},                                                      " +
                "		{                                                       " +
                "			\"id\": \"sample-part-3-id\",                       " +
                "			\"description\": \"sample-part-3-description\"      " +
                "		}                                                       " +
                "	]                                                           ";

        server
                .expect(
                    once(),
                    requestTo(serviceBaseUrl + "/" + ANY_VIN)
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                    withSuccess(
                        mockServerResponse,
                        MediaType.APPLICATION_JSON
                    )
                )
        ;

        // Execute action
        List<PartData> partDataList = partDataServiceProxy.getPartDataList(ANY_VIN);

        // Verify output
        assertEquals(3, partDataList.size());
        assertEquals("sample-part-1-id", partDataList.get(0).getId());
        assertEquals("sample-part-1-description", partDataList.get(0).getDescription());
        assertEquals("sample-part-2-id", partDataList.get(1).getId());
        assertEquals("sample-part-2-description", partDataList.get(1).getDescription());
        assertEquals("sample-part-3-id", partDataList.get(2).getId());
        assertEquals("sample-part-3-description", partDataList.get(2).getDescription());

        // Verify expected interaction with server
        server.verify();

    }




}