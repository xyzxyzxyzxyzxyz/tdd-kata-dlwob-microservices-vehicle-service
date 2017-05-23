package com.tdd.katas.dlwob.microservices.vehicleservice.model;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;

import java.util.List;

public class VehicleInformation {
    private VehicleData vehicleData;
    private CustomerData customerData;
    private List<PartData> partsList;
    private String vin;

    public VehicleData getVehicleData() {
        return vehicleData;
    }
    public void setVehicleData(VehicleData vehicleData) {
        this.vehicleData = vehicleData;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public List<PartData> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<PartData> partsList) {
        this.partsList = partsList;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
