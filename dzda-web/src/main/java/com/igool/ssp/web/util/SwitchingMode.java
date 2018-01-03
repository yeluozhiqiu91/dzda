package com.igool.ssp.web.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by wlj on 17/10/17.
 */
@Component
public class SwitchingMode {

    private static final Logger logger = LoggerFactory.getLogger(SwitchingMode.class);
    @Value("#{config['endpoint']}")
    String endpoint;

    @Value("#{config['accessKeyId']}")
    String accessKeyId;

    @Value("#{config['accessKeySecret']}")
    String accessKeySecret;

    @Value("#{config['bucketName']}")
    String bucketName;

    @Value("#{config['uploadPath']}")
    String uploadPath;

    public String switchingNet(String type, String zeName, InputStream inputStream) throws BizException{
        logger.info("switchingNet() - star,type:"+type+", zeName:"+zeName+", inputStream:"+inputStream);
        String result = "";
        String suffix = zeName.substring(zeName.lastIndexOf("."),zeName.length());
        if (ConstantsEnum.netType.oss.getValue().equals(type)){
            String uuid = UUID.randomUUID().toString();
            uuid= uuid.replaceAll("-","")+suffix;
            logger.info("switchingNet() - uuid:"+uuid);
            try {
                UpLoadOSSClient upLoadOSSClient = new UpLoadOSSClient();
                String etag =upLoadOSSClient.fileInputStreamOSS(endpoint,accessKeyId,accessKeySecret,bucketName,uuid,inputStream);
                if(StringUtils.isEmpty(etag)){
                    logger.warn(ExceptionEnum.OSS存储异常.name());
                    throw new BizException(ExceptionEnum.OSS存储异常.getCode(), ExceptionEnum.OSS存储异常.name());
                }
                logger.info("oss -------> etag:"+etag);
            } catch (ClientException e){
                logger.warn("尝试向OSS发送请求以及数据传输时遇到异常, code:"+e.getErrorCode()+", message:"+e.getErrorMessage());
                throw new BizException(e.getErrorCode(),e.getErrorMessage());
            } catch (OSSException e){
                logger.warn("服务器端错误,code:"+e.getErrorCode()+", message:"+e.getErrorMessage());
                throw new BizException(e.getErrorCode(), e.getErrorMessage());
            } catch (IOException e) {
                logger.warn("oss存储, io异常, message:"+e.getMessage());
                throw new BizException(e.getMessage());
            } catch (Exception e) {
                logger.warn(ExceptionEnum.OSS存储异常.name()+", message:"+e.getMessage());
                throw new BizException(ExceptionEnum.OSS存储异常.getCode(), e.getMessage());
            }
            result = endpoint.replace("http://","http://"+bucketName+".")+"/"+uuid;
        }else if (ConstantsEnum.netType.service.getValue().equals(type)){
            BigFileUpload bf = new BigFileUpload();
            String json = null;
            try {
                json = bf.upload(uploadPath, zeName, inputStream);
            } catch (IOException e) {
                logger.warn("service存储, io异常, message:"+e.getMessage());
                throw new BizException(e.getMessage());
            } catch (Exception e) {
                logger.warn("service存储异常, message:"+e.getMessage());
                throw new BizException(e.getMessage());
            }
            logger.info("service -------> json: "+json);
            JSONObject jsonObject = JSONObject.fromObject(json);
            result = String.valueOf(jsonObject.get("fileId"));
        }
        logger.info("switchingNet() - end.");
        return result;
    }

//    public static void main(String[] args){
//        //createOssClient();
//        //byteUpload();
//        try {
//            InputStream inputStream = null;
//            inputStream = new FileInputStream("/Users/fangzetao/Documents/test/1/1/1/00120170509663c3b86.jpg");
//            SwitchingMode switchingMode = new SwitchingMode();
//            switchingMode.switchingNet("oss","00120170509663c3b86.jpg",inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (BizException e) {
//            e.printStackTrace();
//        }
//    }
}
