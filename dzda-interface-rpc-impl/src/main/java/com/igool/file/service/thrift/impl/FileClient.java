package com.igool.file.service.thrift.impl;

import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.FileTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by igool on 2017/8/17.
 */
@Component
public class FileClient {
    private static final Logger logger = LoggerFactory.getLogger(FileClient.class);

    @Autowired
    private FileSource fileSource;
    private FileTemplate fileTemplate;

    @PostConstruct
    public void init(){
        fileTemplate = new FileTemplate(fileSource.getConnection());
    }

    public FileTemplate getFileTemplate(){
        return fileTemplate;
    }
}
