package com.igool.test.fiel.service.thrift;

import java.nio.ByteBuffer;

/**
 * Created by igool on 2017/6/29.
 */
public interface TestService {
    void deleteFile(String filePath, boolean isPub);
    String storeFile(String fileName, ByteBuffer fileSource, boolean isPub);
}
