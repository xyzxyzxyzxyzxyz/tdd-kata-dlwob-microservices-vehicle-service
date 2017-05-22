package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PartDataController.class)
@WithMockUser
public class PartDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartDataService partDataService;

    @Test
    public void Returns_404_for_non_existent_vin_code() throws Exception {
        String NON_EXISTENT_VIN = "NON_EXISTENT_VIN";

        given(
                partDataService.getPartsByVinCode( NON_EXISTENT_VIN )
            )
            .willReturn(null);

        mockMvc.perform(
                get(PartDataController.URL_MAPPING + "/{vinCode}", NON_EXISTENT_VIN)
            ).andExpect( status().isNotFound() );

        verify(partDataService).getPartsByVinCode( NON_EXISTENT_VIN );
    }

    @Test
    public void Returns_500_when_service_error() throws Exception {
        String ANY_VIN = "ANY_VIN";

        given
            (
                partDataService.getPartsByVinCode( ANY_VIN )
            )
            .willThrow( new RuntimeException("Database not ready") );

        mockMvc.perform(
                get(PartDataController.URL_MAPPING + "/{vinCode}", ANY_VIN)
        ).andExpect( status().isInternalServerError() );

        verify(partDataService).getPartsByVinCode( ANY_VIN );
    }

}
