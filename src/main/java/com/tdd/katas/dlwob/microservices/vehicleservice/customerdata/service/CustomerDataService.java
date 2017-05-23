package com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;

public interface CustomerDataService {
    CustomerData getCustomerData(String customerId);
}
