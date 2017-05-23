package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositeVehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleCustomerDataService vehicleCustomerDataService;
    @Autowired
    private CustomerDataServiceProxy customerDataServiceProxy;
    @Autowired
    private VehicleDataServiceProxy vehicleDataServiceProxy;
    @Autowired
    private PartDataServiceProxy partDataServiceProxy;

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {

        VehicleCustomerData vcd = vehicleCustomerDataService.getVehicleCustomerData(vinCode);
        if (vcd == null) {
            return null;
        }

        CustomerData cd = customerDataServiceProxy.getCustomerData(vcd.getCustomerId());
        VehicleData vd = vehicleDataServiceProxy.getVehicleData(vinCode);
        List<PartData> pdl =  partDataServiceProxy.getPartDataList(vinCode);

        VehicleInformation vinfo = new VehicleInformation();
        vinfo.setVin(vinCode);
        vinfo.setCustomerData(cd);
        vinfo.setVehicleData(vd);
        vinfo.setPartsList(pdl);

        return vinfo;
    }

}
