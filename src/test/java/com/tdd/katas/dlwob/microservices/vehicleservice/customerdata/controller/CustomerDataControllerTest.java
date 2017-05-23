package com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.service.CustomerDataService;
import com.tdd.katas.dlwob.microservices.vehicleservice.customerdata.service.MockServicesConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    
    @Test
    public void Returns_500_when_service_error() throws Exception {

        String ANY_CUSTOMER_ID = "ANY_CUSTOMER_ID";

        given(
                customerDataService.getCustomerData(ANY_CUSTOMER_ID)
            )
            .willThrow(new RuntimeException("Database is not ready"));


        mockMvc
            .perform(
                get(CustomerDataController.URL_MAPPING + "/{customerId}", ANY_CUSTOMER_ID)
            )
            .andExpect( status().isInternalServerError() );


        verify (customerDataService).getCustomerData( ANY_CUSTOMER_ID );

    }


    @Test
    public void Returns_valid_data_for_existing_customer_id() throws Exception {
        CustomerData expectedCustomerData = new CustomerData();
        expectedCustomerData.setId(MockServicesConstants.SAMPLE_CUSTOMER_ID);
        expectedCustomerData.setName("Sergio");
        expectedCustomerData.setSurnames("Osuna Medina");

        given ( customerDataService.getCustomerData( expectedCustomerData.getId() ) )
                .willReturn(expectedCustomerData);


        mockMvc
                .perform(
                    get(CustomerDataController.URL_MAPPING + "/{customerId}", expectedCustomerData.getId())
                )
                .andExpect(
                    status().isOk()
                )
                .andExpect( jsonPath("$.id", is ( expectedCustomerData.getId() ) ))
                .andExpect( jsonPath("$.name", is ( expectedCustomerData.getName() ) ))
                .andExpect( jsonPath("$.surnames", is ( expectedCustomerData.getSurnames() ) ))
        ;


        verify (customerDataService).getCustomerData(expectedCustomerData.getId());
    }



}
