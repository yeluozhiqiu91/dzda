package com.igool.file.service.thrift.impl;

import com.facebook.swift.service.ThriftService;
import com.facebook.swift.codec.ThriftField;
import com.igool.file.service.thrift.FileService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.thrift.TException;
import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.file.FileHandleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by igool on 2017/7/26.
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileClient fileClient;
    public void close() {
        this.close();
    }
    @Override
    public boolean deleteFile(String filePath, boolean isPub) throws TException {
        if (!filePath.startsWith("http://")) {
            filePath = "http://" + filePath;
        }
        String[] urls = filePath.split("/");
        if (urls.length < 4) {
            return false;
        }
        //String protool = urls[0] + "//" + urls[2];
        String fileId = urls[3];
        try {
            fileClient.getFileTemplate().deleteFile(fileId);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error delete weedfs file ",e);
        }
        return false;
    }

    @Override
    public String storeFile(String fileName, byte[] byteFileSource, boolean isPub) throws TException {
        try {
            boolean isStream = fileName.matches("(/*.mp4|/*.mp3)");
            FileHandleStatus fileHandleStatus = fileClient.getFileTemplate().saveFileByStream(fileName, new ByteArrayInputStream(byteFileSource), isStream ? ContentType.APPLICATION_OCTET_STREAM: ContentType.DEFAULT_BINARY);
            return fileClient.getFileTemplate().getFileUrl(fileHandleStatus.getFileId());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error save weedfs file ",e);
        }
        return StringUtils.EMPTY;
    }
}
