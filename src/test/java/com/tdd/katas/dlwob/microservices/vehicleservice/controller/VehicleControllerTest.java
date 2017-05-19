package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleController.class)
@WithMockUser
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Returns_404_for_non_existent_vin_code() throws Exception {
        String NON_EXISTENT_VIN = "NON_EXISTENT_VIN";

        mockMvc.perform(
                get(VehicleController.URL_MAPPING + "/{vinCode}", NON_EXISTENT_VIN)
            ).andExpect(status().isNotFound());
    }

}
