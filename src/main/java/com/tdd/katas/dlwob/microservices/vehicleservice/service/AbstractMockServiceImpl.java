package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

abstract class AbstractMockServiceImpl<DATA> {

    public AbstractMockServiceImpl(Class<DATA> dataClass) {
        readJsonFile(dataClass);
    }

    protected DATA dataObject;

    protected void readJsonFile(Class<DATA> dataClass) {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);

            dataObject = objectMapper.readValue(resourceInputStream, dataClass);
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error reading from JSON file", e);
        } finally {
            if (resourceInputStream!=null) {
                try {
                    resourceInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
