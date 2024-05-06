package com.cyberspeed.utils.impl;

import com.cyberspeed.utils.JsonConvertor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class JsonConvertorImpl implements JsonConvertor {

    private final ObjectMapper objectMapper;


    public JsonConvertorImpl() {
        this.objectMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
    }

    @Override
    public <T> T readValue(File src, Class<T> valueType) throws IOException {
        return objectMapper.readValue(src, valueType);
    }

    @Override
    public void writeValue(OutputStream os, Object object) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(os, object);
    }
}
