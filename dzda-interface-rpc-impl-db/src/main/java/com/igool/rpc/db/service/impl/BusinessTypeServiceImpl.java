package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.BusinessTypeDetail;
import com.igool.rpc.db.mysql.mapper.BusinessInfoMapper;
import com.igool.rpc.db.mysql.mapper.BusinessTypeMapper;
import com.igool.rpc.db.model.thrift.BusinessTypeInfo;
import com.igool.rpc.db.service.thrift.BusinessTypeService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/9/25.
 */
public class BusinessTypeServiceImpl implements BusinessTypeService{
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<BusinessTypeInfo> getAllBusinessType() throws TException {
        return businessTypeMapper.getAllBusinessType();
    }

    @Override
    public List<BusinessTypeDetail> findBusinessTypeDetailByYwlx(@ThriftField(value = 1, name = "ywlx", requiredness = ThriftField.Requiredness.NONE) String ywlx) throws TException {
        BusinessTypeInfo businessTypeInfo=businessTypeMapper.findBusinessTypeByYwlx(ywlx);
        if(businessTypeInfo==null){
            return new ArrayList<>();
        }else{
            List<BusinessTypeDetail> businessTypeDetails=businessTypeMapper.findBusinessTypeDetailByTypeId(businessTypeInfo.getBusinessTypeId());
            if(businessTypeDetails!=null&businessTypeDetails.size()>0){
                return businessTypeDetails;
            }else{
                return new ArrayList<>();
            }
        }
    }

    @Override
    public BusinessTypeInfo findBusinessTypeByYwlx(@ThriftField(value = 1, name = "ywlx", requiredness = ThriftField.Requiredness.NONE) String ywlx) throws TException {
        BusinessTypeInfo businessTypeInfo=businessTypeMapper.findBusinessTypeByYwlx(ywlx);
        if(businessTypeInfo!=null){
            return businessTypeInfo;
        }else{
            return new BusinessTypeInfo();
        }
    }

    @Override
    public List<BusinessTypeDetail> findBusinessTypeDetailAllBybusinessCode(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh) throws TException {
        BusinessInfo businessInfo=businessInfoMapper.findNativeBusinessInfoByLsh(lsh);
        if(businessInfo==null||businessInfo.getBusinessType()==null){
            return new ArrayList<>();
        }
        BusinessTypeInfo businessTypeInfo=businessTypeMapper.findBusinessTypeByYwlx(businessInfo.getBusinessType());
        if(businessTypeInfo==null){
            return new ArrayList<>();
        }
        List<BusinessTypeDetail> businessTypeDetailList = businessTypeMapper.
                findBusinessTypeDetailByBusinessTypeIdAndIsNecessity(businessTypeInfo.getBusinessTypeId(),null);
        List<BusinessTypeDetail> dtos = new ArrayList<>();
        if(businessTypeDetailList!=null&& businessTypeDetailList.size()>0){

            return businessTypeDetailList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public BusinessTypeInfo findBusinessTypeByLsh(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh) throws TException {
        BusinessTypeInfo businessTypeInfo=businessTypeMapper.findBusinessTypeByLsh(lsh);
        if(businessTypeInfo!=null){
            return businessTypeInfo;
        }else{
            return new BusinessTypeInfo();
        }
    }

}
