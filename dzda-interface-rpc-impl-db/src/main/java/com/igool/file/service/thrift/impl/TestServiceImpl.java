package com.igool.file.service.thrift.impl;


import com.igool.file.service.thrift.TestService;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by igool on 2017/7/26.
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    public void close() {
        this.close();
    }
    @Override
    public boolean deleteFile(String filePath, boolean isPub) throws TException {

        return false;
    }
    @Override
    public String storeFile(String fileName, byte[] byteFileSource, boolean isPub) throws TException {
        logger.info("db test");
        return StringUtils.EMPTY;
    }
}
