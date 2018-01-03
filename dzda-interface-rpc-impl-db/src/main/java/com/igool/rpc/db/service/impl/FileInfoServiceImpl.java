package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.mysql.mapper.BusinessInfoMapper;
import com.igool.rpc.db.mysql.mapper.BusinessTypeMapper;
import com.igool.rpc.db.mysql.mapper.FileInfoMapper;
import com.igool.rpc.db.service.thrift.FileInfoService;
import com.igool.util.ConstantUtil;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/9/6.
 */
public class FileInfoServiceImpl implements FileInfoService {
    Logger logger = LoggerFactory.getLogger(FileInfoServiceImpl.class);
    @Autowired
    FileInfoMapper fileInfoMapper;
    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Autowired
    BusinessTypeMapper businessTypeMapper;

    @Override
    public void close() {
        this.close();
    }

    @Override
    public void addFileInfo(@ThriftField(value = 1, name = "businessinfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessinfo) throws TException {


    }

    @Override
    public Map<FileInfo, List<BusinessInfo>> findFileInfoAndBusinessInfoByFileCode(@ThriftField(value = 1, name = "fileCode", requiredness = ThriftField.Requiredness.NONE) String fileCode) throws TException {
        Map map = new HashMap();
        FileInfo fileInfo = fileInfoMapper.findFileInfoByFileCode(fileCode);
        if(fileInfo != null ){
            List<BusinessInfo> businessInfos = businessInfoMapper.findBusinessInfoByFileCode(fileInfo.getFileId()+"");
            map.put(fileInfo,businessInfos);
        }
        return map;
    }

    @Override
    public void updateLshStatus(@ThriftField(value = 1, name = "type", requiredness = ThriftField.Requiredness.NONE) String type, @ThriftField(value = 2, name = "businessId", requiredness = ThriftField.Requiredness.NONE) String businessId, @ThriftField(value = 3, name = "fileId", requiredness = ThriftField.Requiredness.NONE) String fileId) throws TException {
        logger.info(" updateLshStatus -begin  type : "+type +"  businessId : "+businessId + "  fileId : "+ fileId);
        int qsStatus=0;
        int status=0;
        if(type.equals("1")){   //修改为正常
            qsStatus=ConstantUtil.ZC_BUSINESS;
        }else if(type.equals("2")){ //修改为缺失流水
            qsStatus=ConstantUtil.QS_BUSINESS;
            status= ConstantUtil.WT_FILE;
        }else if(type.equals("3")){ //修改为缺失资料
            qsStatus=ConstantUtil.ZL_QS_BUSINESS;
            status=ConstantUtil.WT_FILE;
        }
        businessInfoMapper.updateQsStatus(qsStatus,businessId);
        if(qsStatus != 1){
            fileInfoMapper.updateStatus(status,fileId);
        }else {
            int num = businessInfoMapper.countQsStatusByFileId(fileId,ConstantUtil.QS_BUSINESS,ConstantUtil.ZL_QS_BUSINESS);
            if (num==0) {
                status = ConstantUtil.ZC_FILE;
            }else {
                status = ConstantUtil.WT_FILE;
            }
            fileInfoMapper.updateStatus(status,fileId);
        }
        logger.info(" updateLshStatus -end  ");
    }

    @Override
    public List<BusinessTypeDetail> getBusinessTypeDetailByBusinessType(@ThriftField(value = 1, name = "businessId", requiredness = ThriftField.Requiredness.NONE) String businessId, @ThriftField(value = 2, name = "businessType", requiredness = ThriftField.Requiredness.NONE) String businessType) throws TException {
        List<BusinessTypeDetail> businessTypeDetails = businessTypeMapper.getBusinessTypeDetailByBusinessType(businessId,businessType);

        return businessTypeDetails;
    }

    @Override
    public FileInfo findFileInfoByFileCode(String fileCode) throws TException {
        FileInfo fileInfo=fileInfoMapper.findFileInfoByFileCode(fileCode);
        if(fileInfo!=null){
            return fileInfo;
        }else{
            return new FileInfo();
        }
    }


}
