package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.VehicleDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleDataController.class)
@WithMockUser
public class VehicleDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleDataService vehicleDataService;

    @Test
    public void Returns_404_for_non_existent_vin_code() throws Exception {

        given(
                vehicleDataService.getVehicleData( any() )
            )
            .willReturn(null);

        String NON_EXISTENT_VIN = "NON_EXISTENT_VIN";

        mockMvc.perform(
                get(VehicleDataController.URL_MAPPING + "/{vinCode}", NON_EXISTENT_VIN)
            ).andExpect(status().isNotFound());

        verify(vehicleDataService).getVehicleData( any() );

    }


}