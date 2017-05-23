package com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.MockServicesConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MockCustomerDataServiceImplTest {

    @Test
    public void Contains_sample_data() {
        CustomerDataService mockCustomerDataService = new MockCustomerDataServiceImpl();

        CustomerData actualCustomerData = mockCustomerDataService.getCustomerData(MockServicesConstants.SAMPLE_CUSTOMER_ID);

        assertNotNull(actualCustomerData);
        assertEquals(MockServicesConstants.SAMPLE_CUSTOMER_ID, actualCustomerData.getId());
        assertEquals("Sergio", actualCustomerData.getName());
        assertEquals("Osuna Medina", actualCustomerData.getSurnames());
    }

}
