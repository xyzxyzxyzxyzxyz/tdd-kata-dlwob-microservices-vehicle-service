package com.tdd.katas.dlwob.microservices.vehicleservice.partdata.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.partdata.model.PartData;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MockPartDataServiceImplTest {

    @Test
    public void Contains_sample_data() throws UnsupportedEncodingException {
        PartDataService mockPartDataService = new MockPartDataServiceImpl();

        List<PartData> actualPartDataList = mockPartDataService.getPartsByVinCode(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE);

        assertNotNull(actualPartDataList);
        assertEquals(3, actualPartDataList.size());
        assertEquals("sample-part-1-id", actualPartDataList.get(0).getId());
        assertEquals("sample-part-1-description", actualPartDataList.get(0).getDescription());
        assertEquals("sample-part-2-id", actualPartDataList.get(1).getId());
        assertEquals("sample-part-2-description", actualPartDataList.get(1).getDescription());
        assertEquals("sample-part-3-id", actualPartDataList.get(2).getId());
        assertEquals("sample-part-3-description", actualPartDataList.get(2).getDescription());
    }

}
