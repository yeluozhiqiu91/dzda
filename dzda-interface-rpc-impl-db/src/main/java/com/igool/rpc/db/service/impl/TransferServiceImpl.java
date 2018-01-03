package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.mysql.mapper.*;
import com.igool.rpc.db.service.thrift.FileInfoService;
import com.igool.rpc.db.service.thrift.TransferService;
import com.igool.util.ConstantUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang on 2017/10/9.
 */
public class TransferServiceImpl implements TransferService{
    @Autowired
    private DeliveryInfoMapper deliveryInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Autowired
    SigningMapper signingMapper;
    @Autowired
    FileInfoMapper fileInfoMapper;
    private static SimpleDateFormat sf=new SimpleDateFormat();
    @Override
    public void close() {
        this.close();
    }

    @Override
    public BusinessInfo findNativeBusinessInfo(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh) throws TException {
        BusinessInfo businessInfo= businessInfoMapper.findBusinessInfoByCode(lsh);
        if(businessInfo!=null){
            return businessInfo;
        }else{
            return new BusinessInfo();
        }
    }

    @Override
    public DeliveryInfo findDeliveryInfoByPcCode(@ThriftField(value = 1, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode) throws TException {
        DeliveryInfo deliveryInfo=deliveryInfoMapper.findDeliveryInfoByPcCode(pcCode);
        if(deliveryInfo!=null){
            return deliveryInfo;
        }else{
            return new DeliveryInfo();
        }
    }

    @Override
    public List<DeliveryInfo> getDeliveryListBza(@ThriftField(value = 1, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode, @ThriftField(value = 2, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 3, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 4, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 5, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<DeliveryInfo> deliveryInfoList=deliveryInfoMapper.getDeliveryListBza(pcCode,startTime,endTime,new RowBounds(startIndex,pageSize));
        if(deliveryInfoList!=null&&deliveryInfoList.size()>0){
            //查用户
            List<UserInfo> userInfoList=userInfoMapper.getAllUser();
            Map<Integer,String> userMap=new HashMap<>();
            for(UserInfo u:userInfoList){
                userMap.put(u.getUserId(),u.getName());
            }
            for(DeliveryInfo d:deliveryInfoList){
                d.setRegisterPersonName(userMap.get(d.getRegisterPerson())==null?"未知":userMap.get(d.getRegisterPerson()));
                d.setSignPersonName(userMap.get(d.getSignPerson())==null?"":userMap.get(d.getSignPerson()));
                if(d.getStatus()==0){
                    d.setStatusStr("已登记");
                }else if(d.getStatus()==1){
                    d.setStatusStr("已签收");
                }else if(d.getStatus()==2){
                    d.setStatusStr("签收中");
                }
            }
            return deliveryInfoList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public long getDeliveryListCountBza(@ThriftField(value = 1, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode, @ThriftField(value = 2, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 3, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 4, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 5, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return deliveryInfoMapper.getDeliveryListCountBza(pcCode,startTime,endTime,new RowBounds(startIndex,pageSize));
    }

    @Override
    public List<BusinessInfo> findBusinessInfoByPcCodeAndLszt(@ThriftField(value = 1, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode, @ThriftField(value = 2, name = "lszt", requiredness = ThriftField.Requiredness.NONE) int lszt) throws TException {
        List<BusinessInfo> businessInfoList=businessInfoMapper.findNativeBusinessInfoByDeliveryPcCodeAndLszt(pcCode,lszt);
        if(businessInfoList!=null&&businessInfoList.size()>0){
            return businessInfoList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public void BToASign(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode, @ThriftField(value = 3, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId) throws TException {
        //查t_da_signing是否有对应的批次记录，无则新建，
        Signing signing=signingMapper.findSigningByPcCode(pcCode);
        if(signing==null){
            signing=new Signing();
            signing.setCode(pcCode);
            signing.setReceiverDate(sf.format(new Date()));
            signing.setReceiver(userId);
            signing.setCreateDate(sf.format(new Date()));
            signing.setStatus(0);
            signing.setJjlx(ConstantUtil.B_TO_A_QS);
            signingMapper.saveSigning(signing);
        }
        SigningRel signingRel=new SigningRel();
        signingRel.setSigningId(signing.getSigningId());
        signingRel.setCreateUser(userId);
        signingRel.setBusinessId(businessInfo.getBusinessId());
        signingMapper.saveSigningRel(signingRel);//添加批次明细
        businessInfoMapper.updateBusinessInfoWzAndZtById(businessInfo.getBusinessId(),ConstantUtil.B_TO_A_QS,ConstantUtil.A);//跟新流水位置和状态
        //t_da_signing_rel表插入相应记录，然后流水状态改为B转A签收
        //查该批次对应的登记数，签收数，若签收数小于登记数，则t_da_signing表status改为签收中，若等于，则改为已签收
        int djCount=deliveryInfoMapper.getDjCountByPcCode(pcCode);
        int qsCount=signingMapper.getQsCountByPcCode(pcCode);
        if(qsCount<djCount){
            signing.setStatus(ConstantUtil.STATUS_QSZ);
        }else if(qsCount==djCount){
            signing.setStatus(ConstantUtil.STATUS_YQS);
        }
        signingMapper.updateSigingStatus(signing);
    }

    @Override
    public List<FileId> findAllFileIdsByCkStatus(int ckStatus) throws TException {
        List<FileId> fileIdList=fileInfoMapper.findAllFileIdsByCkStatus(ckStatus);
        if(fileIdList!=null&&fileIdList.size()>0){
            return fileIdList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<FileId> findAllForFileCode() throws TException {
        List<FileId> fileIdList=fileInfoMapper.findAllForFileCode();
        if(fileIdList!=null&&fileIdList.size()>0){
            return fileIdList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<FileId> findAllForBox() throws TException {
        List<FileId> fileIdList=fileInfoMapper.findAllForBox();
        if(fileIdList!=null&&fileIdList.size()>0){
            return fileIdList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public int findAllForFileCodeCount() throws TException {
        return fileInfoMapper.findAllForFileCodeCount();
    }

    @Override
    public int findAllForBoxCount() throws TException {
        return 0;
    }

    @Override
    public List<FileId> findForFileCode(int startIndex, int pageSize) throws TException {
        return fileInfoMapper.findForFileCode(startIndex,pageSize);
    }

}
