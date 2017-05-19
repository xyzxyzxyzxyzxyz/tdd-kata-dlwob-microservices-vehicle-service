package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;

public class MockCustomerDataServiceImpl
        extends AbstractMockServiceImpl<CustomerData>
        implements CustomerDataService
{

    public MockCustomerDataServiceImpl() {
        super(CustomerData.class);
    }

    @Override
    public CustomerData getCustomerData(String customerId) {
        if (MockServicesConstants.SAMPLE_CUSTOMER_ID.equals(customerId)) {
            return dataObject;
        }
        else {
            return null;
        }
    }

}
