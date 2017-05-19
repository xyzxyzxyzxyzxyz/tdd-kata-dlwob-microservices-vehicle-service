package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;

public interface CustomerDataService {
    CustomerData getCustomerData(String customerId);
}
