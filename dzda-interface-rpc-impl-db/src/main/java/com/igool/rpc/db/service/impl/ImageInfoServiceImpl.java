package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mapper.ImagePhotoRelMapper;
import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.mysql.mapper.BusinessInfoMapper;
import com.igool.rpc.db.mysql.mapper.BusinessTypeMapper;
import com.igool.rpc.db.mysql.mapper.ImageInfoMapper;
import com.igool.rpc.db.mysql.mapper.UserInfoMapper;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.util.ConstantsEnum;
import com.igool.util.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang on 2017/10/17.
 */
public class ImageInfoServiceImpl implements ImageInfoService {
    @Autowired
    ImageInfoMapper imageInfoMapper;
    @Autowired
    ImagePhotoRelMapper imagePhotoRelMapper;
    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    public static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger= LoggerFactory.getLogger(ImageInfoServiceImpl.class);

    @Value("${scanner.image.type}")
    String type;

    @Override
    public void close() {
        this.close();
    }

    @Override
    public void addOrUpdataInfoByDTO(@ThriftField(value = 1, name = "imagePhotoRelDTO", requiredness = ThriftField.Requiredness.NONE) ImagePhotoRelDTO imagePhotoRelDTO) throws TException {
        logger.info("addOrUpdataInfoByDTO() - begin,DTO:" + imagePhotoRelDTO.toString());
        String lsh = imagePhotoRelDTO.getLsh();//流水号
        Integer orderNum = imagePhotoRelDTO.getOrderNum();//排序号
        String photoCode= imagePhotoRelDTO.getPhotoCode();//图片id
        String operatorType=imagePhotoRelDTO.getOperatorType();//操作类型
        Integer imageInfoId = null;
        ImageInfo imageInfoOld=imageInfoMapper.findImageInfoByLsh(lsh);
        if(imageInfoOld==null){ //没有影像记录
            BusinessTypeInfo businessTypeInfo=businessTypeMapper.findBusinessTypeByLsh(lsh);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setCode(lsh);
            imageInfo.setStatus(ConstantsEnum.imageStatus.采集中.getValue());
            imageInfo.setApplicationDate(sf.format(new Date()));
            imageInfo.setBusinessTypeInfoId(businessTypeInfo.getBusinessTypeId());
            imageInfo.setImageUser(imagePhotoRelDTO.getUserId());
            imageInfo.setImageDate(sf.format(new Date()));
            imageInfoMapper.addImageInfo(imageInfo);
            imageInfoId = imageInfo.getImageId();//生成影像id
        }else{
            imageInfoOld.setImageUser(imagePhotoRelDTO.getUserId());
            imageInfoOld.setImageDate(sf.format(new Date()));
            imageInfoOld.setStatus(ConstantsEnum.imageStatus.采集中.getValue());
            imageInfoMapper.updataImageInfo(imageInfoOld);
            imageInfoId = imageInfoOld.getImageId();//生成影像id
        }
        //添加数据进业务配置图像关联表
        logger.info("addOrUpdataInfoByDTO() - imageInfoId:" + imageInfoId + ",orderNum:" + orderNum);
        List<ImagePhotoRel> list=null;
        if(ConstantsEnum.imageType.新增.getValue().equals(operatorType)||ConstantsEnum.imageType.修改.getValue().equals(operatorType)){
            //若是新增或修改，就按影像id和图片顺序来查，因为只有新增和修改才会改变photeCode
            list = imagePhotoRelMapper.findImagePhotoRelByImageIdAndOrderNum(imageInfoId, orderNum);
        }else{
            //若是其他操作则按影像id和photoCode来查(因为其他操作的photoCode不变)
            list = imagePhotoRelMapper.findImagePhotoRelByImageIdAndPhotoCode(imageInfoId, photoCode);
        }
        if(list!=null&&list.size()>0){
            ImagePhotoRel imagePhotoRel = list.get(0);
            imagePhotoRel.setPhoto(imagePhotoRelDTO.getPhotoCode());
            imagePhotoRel.setOrderNum(orderNum);
            imagePhotoRel.setBtdId(imagePhotoRelDTO.getBusinessDetailId());
            String url = imagePhotoRelDTO.getUrl();
            if (StringUtils.isNotEmptyByTrim(url)) {
                imagePhotoRel.setUrl(url);
            }
            imagePhotoRelMapper.updateImagePhotoRel(imagePhotoRel);
        }else{
            ImagePhotoRel imagePhotoRel=new ImagePhotoRel();
            imagePhotoRel.setImageId(imageInfoId);
            imagePhotoRel.setPhoto(photoCode);
            imagePhotoRel.setOrderNum(orderNum);
            imagePhotoRel.setBtdId(imagePhotoRelDTO.getBusinessDetailId());
            imagePhotoRel.setUrl(imagePhotoRelDTO.getUrl());
            //imagePhotoRelMapper.insert(imagePhotoRel);
            imagePhotoRelMapper.addImagePhotoRel(imagePhotoRel);
        }
        logger.info("addOrUpdataInfoByDTO() - end");
    }

    @Override
    public void updataImageStatus(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh, @ThriftField(value = 2, name = "status", requiredness = ThriftField.Requiredness.NONE) int status) throws TException {
        logger.info("updataImageStatus() -begin ,lsh:" + lsh);
        ImageInfo imageInfo=imageInfoMapper.findImageInfoByLsh(lsh);
        if(imageInfo!=null){
            imageInfo.setStatus(status);
            imageInfoMapper.updataImageInfo(imageInfo);
        }
    }

    @Override
    public BusinessImageCount findBusinessImageCount(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        logger.info("findBusinessImageCount() -begin ,str：" + startTime + " ,end:" + endTime);

        BusinessImageCount businessImageCount = new BusinessImageCount();
        businessImageCount.setManageDept("武汉市车管所");

        int carCount = imageInfoMapper.findDriverImageCountByDate(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"", type);
        businessImageCount.setDriverCount(carCount);
        List<Map<String,String>> maps = imageInfoMapper.findBusinessImageCount(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"", type);
        List<String> type = new ArrayList<>();
        List<String> num = new ArrayList<>();
        int businessCount=0;
        for(Map map:maps){
            type.add(map.get("name")+"");
            String a = map.get("count")+"";
            num.add(a);
            businessCount += Integer.parseInt(a);
        }
        businessImageCount.setBusinessNum(num);
        businessImageCount.setBusinessType(type);
        businessImageCount.setBusinessCount(businessCount);
        logger.info("findBusinessImageCount() -end");
        return businessImageCount;
    }

    @Override
    public List<ImageInfo> ajaxStreamNum(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        logger.info("ajaxStreamNum() -begin ,str：" + startTime + " ,end:" + endTime);
        List<BusinessTypeInfo> businessTypeInfos = businessTypeMapper.getAllBusinessType();
        List<UserInfo> userInfos = userInfoMapper.getAllUser();
        Map<Integer,BusinessTypeInfo> businessTypeInfoMap = new HashMap<>();
        for(BusinessTypeInfo bti:businessTypeInfos){
            businessTypeInfoMap.put(bti.getBusinessTypeId(),bti);
        }
        Map<Integer,UserInfo> userInfoMap = new HashMap<>();
        for(UserInfo ui:userInfos){
            userInfoMap.put(ui.getUserId(),ui);
        }
        List<ImageInfo> imageInfos = imageInfoMapper.streamNumByDate(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"");
        if(imageInfos.size()>0){
            List<ImageInfo> dabhs = businessInfoMapper.findBusinessInfoByCodes(imageInfos);
            for( int i=0;i<imageInfos.size();i++ ){
                for( int k=0;k<dabhs.size();k++ ){
                    if(imageInfos.get(i).getCode().equals(dabhs.get(k).getCode())){
                        imageInfos.get(i).setFileCode(dabhs.get(k).getFileCode());
                        BusinessTypeInfo businessTypeInfo = businessTypeInfoMap.get(imageInfos.get(i).getBusinessTypeInfoId());
                        imageInfos.get(i).setBusinessTypeInfoName(businessTypeInfo==null?"":businessTypeInfo.getName());
                        UserInfo userInfo = userInfoMap.get(imageInfos.get(i).getImageUser());
                        imageInfos.get(i).setUserName(userInfo==null?"":userInfo.getName());
                        break;
                    }
                }
            }
        }
        logger.info("ajaxStreamNum() -end");

        return imageInfos;
    }

    @Override
    public List<BusinessInfo> fileCodeDetail(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        logger.info("fileCodeDetail() -begin ,startTime：" + startTime + " ,endTime:" + endTime);
        List<ImageInfo> imageInfos = imageInfoMapper.findImageCodeByDate(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"");
        List<BusinessInfo> businessInfos = businessInfoMapper.getImageFileCodeDetail(imageInfos);
        logger.info("fileCodeDetail() -end");
        return businessInfos;
    }

    @Override
    public List<UserImageCount> userImageCount(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        logger.info("userImageCount -begin  startTime :" + startTime + "  endTime :" + endTime);
        List<UserImageCount> userImageCountBusinessNum = imageInfoMapper.userImageCountBusinessNum(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"");
        List<UserImageCount> userImageCountDriverNum = imageInfoMapper.userImageCountDriverNum(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"");

        for(int i=0;i<userImageCountBusinessNum.size();i++){
            userImageCountBusinessNum.get(i).setManageDept("金信融威");
            for(int q=0;q<userImageCountDriverNum.size();q++){
                if(userImageCountBusinessNum.get(i).getUserId()==userImageCountDriverNum.get(q).getUserId()){
                    userImageCountBusinessNum.get(i).setDriverNum(userImageCountDriverNum.get(q).getDriverNum());
                }
            }
        }
        logger.info("userImageCount() -end");
        return userImageCountBusinessNum;
    }

    @Override
    public List<ImageInfo> businessNumDetailByDateAndUserId(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "userId", requiredness = ThriftField.Requiredness.NONE) String userId) throws TException {
        logger.info("businessNumDetailByDateAndUserId -begin  startTime :" + startTime + "  endTime :" + endTime + "  userId :" + userId);
        List<ImageInfo>  imageInfos = imageInfoMapper.businessNumDetailByDateAndUserId(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"",userId);
        logger.info("businessNumDetailByDateAndUserId() -end");
        return imageInfos;
    }

    @Override
    public List<BusinessInfo> driverNumDetail(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "userId", requiredness = ThriftField.Requiredness.NONE) String userId) throws TException {
        logger.info("driverNumDetail -begin  startTime :" + startTime + "  endTime :" + endTime + "  userId :" + userId);

        List<ImageInfo> imageInfos = imageInfoMapper.findImageCodeByDateAndUserId(startTime!=null&&!startTime.equals("")?startTime+":00":"", endTime!=null&&!endTime.equals("")?endTime+":00":"",userId);
        List<BusinessInfo> businessInfos = businessInfoMapper.getImageFileCodeDetail(imageInfos);
        logger.info("driverNumDetail -end  ");
        return businessInfos;
    }

    @Override
    public int findImagePhotoNumByDate(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        logger.info("findImagePhotoNumByDate -begin  startTime :" + startTime + "  endTime :" + endTime );

        String num = imageInfoMapper.findImagePhotoNumByDate(startTime!=null&&!startTime.equals("")?startTime:"", endTime!=null&&!endTime.equals("")?endTime:"");
        logger.info("findImagePhotoNumByDate -扫描图片总数量  num : "+num);
        logger.info("findImagePhotoNumByDate -end  ");
        return StringUtils.isEmpty(num)?0:Integer.parseInt(num);
    }
}
