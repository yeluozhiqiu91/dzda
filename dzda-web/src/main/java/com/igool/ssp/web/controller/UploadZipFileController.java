package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.BusinessTypeDetail;
import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import com.igool.rpc.db.model.thrift.ImagePhotoRelDTO;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.BusinessTypeService;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.util.*;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wang on 2017/10/17.
 */
@Controller
@AclAnnotation(pUrl = "/uploadZipFile")
public class UploadZipFileController {
    Logger logger= LoggerFactory.getLogger(UploadZipFileController.class);
    @Value("#{config['uploadNet']}")
    String uploadNet;
    @Value("#{config['is.test']}")
    boolean isTest;
    @Autowired
    ImagePhotoRefService imagePhotoRefService;
    @Autowired
    BusinessTypeService businessTypeService;
    @Autowired
    ImageInfoService imageInfoService;
    @Autowired
    BusinessInfoService businessInfoService;
    public static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    SwitchingMode switchingMode;
    @RequestMapping("/uploadZipFile")
    @ResponseBody
    public void uploadZipFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("UpLoadZipFileServlet() - start .");
        response.addHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=GBK");
        // 为解析类提供配置信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(20*1024*1024);
        // 创建解析类的实例
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 每个表单域中数据会封装到一个对应的FileItem对象上
        try{
            List<FileItem> items = sfu.parseRequest(request);
            String lsh = "";
            String userId = "";
            // 区分表单域
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                // isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    // 写入文件
                    logger.info("file write star ");
                    compressFiles(item,lsh,Integer.valueOf(userId));
                    logger.info("file write end ");

                    JSONObject json = new JSONObject();
                    json.put("result", "SUCCEED");
                    String succeed = "已成功上传扫描";
                    item.delete();
                    json.put("message", succeed);
                    logger.info("service - json=" + json + ", fileName = "+item.getName()+", end. ");
                    response.getWriter().print(succeed);
                }else{
                    String name = item.getFieldName();
                    String branch = new String(item.getString().getBytes("ISO-8859-1"),
                            "GBK");
                    String[] b = branch.split("_");
                    lsh = b[0];
                    userId = b[1];
                    logger.info("name:"+name+", lsh"+lsh);
                }
            }
            businessInfoService.addBusinessQszl(lsh,userId);
        }catch (Exception e) {
            JSONObject json = new JSONObject();
            json.put("result", "FAIL");
            json.put("message", e.getMessage());
            logger.warn("UpLoadFileServlet - json=" + json + ", end. ");
            response.getWriter().print("FAIL,"+e.getMessage());
        }
    }


    public void compressFiles(FileItem fileItem, String lsh,Integer userId) throws BizException {
        ZipArchiveInputStream zin = null;
        try {
            logger.info("copressFiles() - star, fileItem"+fileItem);
            zin = new ZipArchiveInputStream(fileItem.getInputStream(),"GBK");

            File f = new File(fileItem.getName());
            if(isTest){//如果是本地环境
                f=new File("d:"+File.separator+"test"+File.separator+"Image.zip");
            }
            //File f=new File("d:"+File.separator+"test"+File.separator+"Image.zip");
            fileItem.write(f);
            ZipFile zf = new ZipFile(f,"GBK");

            ArchiveEntry entry = null;
            InputStream is = null;
            List<BusinessTypeDetail> businessTypeDetailtwoDTOs = businessTypeService.findBusinessTypeDetailAllBybusinessCode(lsh);
            while ((entry = zin.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    logger.info("file - name:" + entry.getName() + ", size:" + entry.getSize() + " bytes");
                    String zeName = entry.getName();
                    is = zf.getInputStream(zf.getEntry(entry.getName()));

                    String[] arr = zeName.split("_");
                    logger.info("arr:"+arr);

                    int businessDetailId = 0;
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(arr[1]);

                    String ywlx = "";//businessInfoDTO.getYwlx();
                    if( !isNum.matches() ){
                        ywlx = arr[1];
                    }else{
                        ywlx = arr[2];
                    }
                    //List<BusinessTypeDetailtwoDTO> businessTypeDetailtwoDTOs = businessTypeInfoService.findBusinessTypeDetailAllBybusinessCode(lsh);
                    if (null != ywlx && StringUtils.isNotEmptyByTrim(ywlx)) {
                        //List<BusinessTypeDetailDTO> btos = businessTypeInfoService.findBusinessTypeInfoByType(ywlx);//businessTypeInfoService.findBusinessTypeInfoByType(ywlx);
                        if (businessTypeDetailtwoDTOs.size() > 0) {
                            for (BusinessTypeDetail businessTypeDetailtwoDTO : businessTypeDetailtwoDTOs){
                                if (ywlx.equals(businessTypeDetailtwoDTO.getDetailName())){
                                    businessDetailId = businessTypeDetailtwoDTO.getBusinessDetailId();
                                    break;
                                }
                            }
                        }
                    }else{
                        logger.warn(ExceptionEnum.业务类型异常.name());
                        throw new BizException(ExceptionEnum.业务类型异常.getCode(),ExceptionEnum.业务类型异常.name());
                    }

                    //操作类型
                    String pType = arr[arr.length-1];
                    pType = pType.substring(0,pType.lastIndexOf("."));

                    if (ConstantsEnum.imageType.删除.getValue().equals(pType)){
                        String photoCode = arr[0];
                        logger.info("删除 - photoCode:" + photoCode);
                        imagePhotoRefService.delImagePhotoRelByLshCodeAndPhotoCode(lsh, photoCode);
                    }else{

                        ImagePhotoRelDTO imagePhotoRelDTO = new ImagePhotoRelDTO();
                        imagePhotoRelDTO.setLsh(lsh);
                        imagePhotoRelDTO.setBusinessDetailId(businessDetailId);
                        imagePhotoRelDTO.setUserId(userId);//扫描人
                        imagePhotoRelDTO.setCreateDate(sf.format(new Date()));//扫描时间
                        imagePhotoRelDTO.setOperatorType(pType);//图片操作类型

                        if(ConstantsEnum.imageType.新增.getValue().equals(pType)
                                || ConstantsEnum.imageType.修改.getValue().equals(pType)){

                            String orderNum = arr[arr.length-2];
                            //转发
                            //SwitchingMode switchingMode = new SwitchingMode();
                            String picId = switchingMode.switchingNet(uploadNet,zeName,is);

                            logger.info("新增、修改 - lsh:" + lsh + ", picId:" + picId+ ", orderNum:" + orderNum+", businessInfoId:"+businessDetailId);

                            if (picId.toLowerCase().startsWith("http")) {
                                imagePhotoRelDTO.setUrl(picId);
                                if (ConstantsEnum.imageType.新增.getValue().equals(pType)){
                                    picId = MD5.MD5Encode(picId);
                                }else{
                                    picId = arr[0];
                                }
                                logger.info("md5:"+picId);
                            }

                            imagePhotoRelDTO.setOrderNum(Integer.valueOf(orderNum));
                            imagePhotoRelDTO.setPhotoCode(picId);

                            imageInfoService.addOrUpdataInfoByDTO(imagePhotoRelDTO);
                        }else if(ConstantsEnum.imageType.排序更新.getValue().equals(pType)){
                            String orderNum = arr[arr.length-2];
                            String oldPhotoCode = arr[0];

                            logger.info("排序更新 - orderNum:" + orderNum + ", oldPhotoCode:" + oldPhotoCode+ ", pType:" + pType+", businessInfoId:"+businessDetailId);

                            imagePhotoRelDTO.setOrderNum(Integer.valueOf(orderNum));
                            imagePhotoRelDTO.setPhotoCode(oldPhotoCode);
                            imageInfoService.addOrUpdataInfoByDTO(imagePhotoRelDTO);

                        }else if(ConstantsEnum.imageType.补全缺失.getValue().equals(pType)){
                            String orderNum = arr[arr.length-2];
                            String oldPhotoCode = arr[0];

                            logger.info("补全缺失 - orderNum:" + orderNum + ", oldPhotoCode:" + oldPhotoCode+ ", pType:" + pType+", businessInfoId:"+businessDetailId);

                            imagePhotoRelDTO.setOrderNum(Integer.valueOf(orderNum));
                            imagePhotoRelDTO.setPhotoCode(oldPhotoCode);
                            imageInfoService.addOrUpdataInfoByDTO(imagePhotoRelDTO);
                        }else{
                            logger.warn("未找到图片类型, pType:"+pType);
                            throw new BizException(ExceptionEnum.参数异常.getCode(),ExceptionEnum.参数异常.name());
                        }

                    }
                }
                entry = null;
            }
            imageInfoService.updataImageStatus(lsh, ConstantsEnum.imageStatus.已采集.getValue());

            if (null!=is) {
                is.close();
            }
            if (null!=zf) {
                zf.close();
            }
            if (null!=zin) {
                zin.close();
            }
            //删除zip 包
            if(f.exists()){
                f.delete();
            }else{
                logger.info("未找到文件，删除失败");
            }
            logger.info("copressFiles() - end");
        }catch (FileNotFoundException e) {
            logger.warn("未找到文件, message:"+e.getMessage());
            throw new BizException(ExceptionEnum.未找到文件.getCode(),ExceptionEnum.未找到文件.name());
        }catch (ArchiveException e) {
            logger.warn("不支持的压缩格式, message:"+e.getMessage());
            throw new BizException(ExceptionEnum.不支持的压缩格式.getCode(),ExceptionEnum.不支持的压缩格式.name());
        } catch (IOException e){
            logger.warn("code:"+ExceptionEnum.解压文件IO异常.getCode()+", message:"+e.getMessage());
            throw new BizException(ExceptionEnum.解压文件IO异常.getCode(),ExceptionEnum.解压文件IO异常.name());
        }catch (BizException e){
            logger.warn("BizException, code:"+e.getCode()+", message:"+e.getMessage());
            throw new BizException("message:"+e.getMessage()+", code:"+e.getCode());
        }catch (Exception e) {
            logger.warn("Exception, message:"+e.getMessage());
            throw new BizException(ExceptionEnum.参数异常.name());
        }finally {
            try {
                if (null != zin){
                    zin.close();
                }
            }catch (IOException e){
                logger.warn("code:"+ExceptionEnum.解压文件IO异常.getCode()+", message:"+e.getMessage());
                throw new BizException(ExceptionEnum.解压文件IO异常.getCode(),ExceptionEnum.解压文件IO异常.name());
            }
        }
    }
}
