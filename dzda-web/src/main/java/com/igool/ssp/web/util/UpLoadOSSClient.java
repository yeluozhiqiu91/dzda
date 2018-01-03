package com.igool.ssp.web.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fangzetao on 17/5/15.
 */
public class UpLoadOSSClient {

    private static final Logger logger = LoggerFactory.getLogger(UpLoadOSSClient.class);


    public String fileInputStreamOSS(String endpoint
            , String accessKeyId, String accessKeySecret, String bucketName, String objectKey, InputStream inputStream) throws IOException {

        logger.info("fileInputStreamOSS() - star, endpoint: "+endpoint
                +", accessKeyId:"+accessKeyId
                +", accessKeySecret:"+accessKeySecret
                +", bucketName:"+bucketName
                +", objectKey:"+objectKey
                +", inputStream:"+inputStream);

        // 创建ClientConfiguration实例，按照您的需要修改默认参数
        ClientConfiguration conf = new ClientConfiguration();
        // 开启支持CNAME选项
        conf.setSupportCname(false);

        OSSClient ossClient = null;
        try {
            // 创建OSSClient实例
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret,conf);
            logger.info("ossClient已实例, endpoint:"+ossClient.getEndpoint());
            // 上传文件流
            boolean flag = ossClient.doesBucketExist(bucketName);
            if(!flag){
                logger.warn("bucket不存在, bucketName:"+bucketName);
                throw new IOException("bucket不存在, bucketName:"+bucketName);
            }
            PutObjectResult result = ossClient.putObject(bucketName, objectKey, inputStream);
            logger.info("PutObjectResult - 文件已上传, result:"+result);
            String etag = result.getETag();
            logger.info("result ----> etag:"+etag);
            logger.info("fileInputStreamOSS() - end.");
            return etag;
        }catch (ClientException e){
            logger.warn("尝试向OSS发送请求以及数据传输时遇到异常, code:"+e.getErrorCode()+", message:"+e.getErrorMessage());
            throw new ClientException(e.getErrorMessage());
        }catch (OSSException e){
            logger.warn("服务器端错误,code:"+e.getErrorCode()+", message:"+e.getErrorMessage());
            throw new OSSException(e.getErrorMessage());
        }finally {
            // 关闭client
            if (ossClient!=null)
                ossClient.shutdown();
            logger.info("ossClient --> shutdown()");
        }

    }



}
