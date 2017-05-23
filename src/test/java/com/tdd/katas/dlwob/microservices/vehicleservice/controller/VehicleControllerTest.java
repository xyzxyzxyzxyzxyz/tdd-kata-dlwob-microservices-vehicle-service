package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.vehicledata.model.VehicleData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleController.class)
@WithMockUser
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    public void Returns_404_for_non_existent_vin_code() throws Exception {
        String NON_EXISTENT_VIN = "NON_EXISTENT_VIN";

        mockMvc.perform(
                get(VehicleController.URL_MAPPING + "/{vinCode}", NON_EXISTENT_VIN)
            ).andExpect(status().isNotFound());
    }

    @Test
    public void Returns_500_when_service_error() throws Exception {

        String ANY_VIN = "ANY_VIN";

        given(vehicleService.getVehicleInformation(any())).willThrow(new RuntimeException("Database is not ready"));

        mockMvc.perform(
                get(VehicleController.URL_MAPPING + "/{vinCode}", ANY_VIN)
        ).andExpect(status().isInternalServerError());

    }

    @Test
    public void Returns_valid_data_for_existing_vin_code() throws Exception {
        VehicleInformation vinfo = new VehicleInformation();
        vinfo.setVin("test-vin");
        vinfo.setVehicleData(new VehicleData());
        vinfo.getVehicleData().setModelId("test-model-id");
        vinfo.getVehicleData().setPlateNumber("test-plate-number");
        vinfo.setCustomerData(new CustomerData());
        vinfo.getCustomerData().setId("test-customer-id");
        vinfo.getCustomerData().setName("test-customer-name");
        vinfo.getCustomerData().setSurnames("test-customer-surnames");
        vinfo.setPartsList(new ArrayList<>());
        vinfo.getPartsList().add(new PartData());
        vinfo.getPartsList().get(0).setId("test-parts-0-id");
        vinfo.getPartsList().get(0).setDescription("test-parts-0-description");
        vinfo.getPartsList().add(new PartData());
        vinfo.getPartsList().get(1).setId("test-parts-1-id");
        vinfo.getPartsList().get(1).setDescription("test-parts-1-description");
        vinfo.getPartsList().add(new PartData());
        vinfo.getPartsList().get(2).setId("test-parts-2-id");
        vinfo.getPartsList().get(2).setDescription("test-parts-2-description");


        given ( vehicleService.getVehicleInformation(any()) )
            .willReturn(vinfo);


        mockMvc
            .perform(
                get(VehicleController.URL_MAPPING + "/{vinCode}", vinfo.getVin())
            )
            .andExpect(
                status().isOk()
            )
            .andExpect( jsonPath("$.vin", is ( vinfo.getVin() ) ))
            .andExpect( jsonPath("$.vehicleData.modelId", is ( vinfo.getVehicleData().getModelId() ) ))
            .andExpect( jsonPath("$.vehicleData.plateNumber", is ( vinfo.getVehicleData().getPlateNumber() ) ))
            .andExpect( jsonPath("$.customerData.id", is ( vinfo.getCustomerData().getId() ) ))
            .andExpect( jsonPath("$.customerData.name", is ( vinfo.getCustomerData().getName() ) ))
            .andExpect( jsonPath("$.customerData.surnames", is ( vinfo.getCustomerData().getSurnames() ) ))
            .andExpect( jsonPath("$.partsList[0].id", is ( vinfo.getPartsList().get(0).getId() ) ))
            .andExpect( jsonPath("$.partsList[0].description", is ( vinfo.getPartsList().get(0).getDescription() ) ))
            .andExpect( jsonPath("$.partsList[1].id", is ( vinfo.getPartsList().get(1).getId() ) ))
            .andExpect( jsonPath("$.partsList[1].description", is ( vinfo.getPartsList().get(1).getDescription() ) ))
            .andExpect( jsonPath("$.partsList[2].id", is ( vinfo.getPartsList().get(2).getId() ) ))
            .andExpect( jsonPath("$.partsList[2].description", is ( vinfo.getPartsList().get(2).getDescription() ) ))
        ;

    }

}
