package com.cyberspeed.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface JsonConvertor {

    <T> T readValue(File src, Class<T> valueType) throws IOException;

    void writeValue(OutputStream os, Object object) throws IOException;

}
