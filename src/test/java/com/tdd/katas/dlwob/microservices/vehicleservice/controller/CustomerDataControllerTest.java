package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.CustomerDataService;
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
@WebMvcTest(controllers = CustomerDataController.class)
@WithMockUser
public class CustomerDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDataService customerDataService;

    @Test
    public void Returns_404_for_non_existent_customer_id() throws Exception {

        String NON_EXISTENT_CUSTOMER_ID = "NON_EXISTENT_CUSTOMER_ID";

        given(
                customerDataService.getCustomerData( NON_EXISTENT_CUSTOMER_ID )
            )
            .willReturn(null);

        mockMvc.perform(
                get(CustomerDataController.URL_MAPPING + "/{customerId}", NON_EXISTENT_CUSTOMER_ID)
            ).andExpect( status().isNotFound() );

        verify(customerDataService).getCustomerData( NON_EXISTENT_CUSTOMER_ID );

    }

}
