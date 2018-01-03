package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mapper.ImagePhotoRelMapper;
import com.igool.rpc.db.model.thrift.ImageInfo;
import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import com.igool.rpc.db.model.thrift.MinAndMaxId;
import com.igool.rpc.db.mysql.mapper.ImageInfoMapper;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by igool on 2017/8/28.
 */
public class ImagePhotoRelServiceImpl implements ImagePhotoRefService {

    @Autowired
    private ImagePhotoRelMapper imagePhotoRelMapper;
    @Autowired
    ImageInfoMapper imageInfoMapper;
    public void close() {
        this.close();
    }

    public void insert(@ThriftField(value = 1, name = "model", requiredness = ThriftField.Requiredness.NONE) ImagePhotoRel model) throws TException {
            imagePhotoRelMapper.insert(model);
    }

    @Override
    public List<ImagePhotoRel> getImagePhotoRelList(int imageId) throws TException {
        return imagePhotoRelMapper.getImagePhotoRelList(imageId);
    }

    @Override
    public List<ImagePhotoRel> findImageRelByLshAndBusinessTypeId(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh, @ThriftField(value = 2, name = "businessTypeId", requiredness = ThriftField.Requiredness.NONE) int businessTypeId) throws TException {
        ImageInfo imageInfo=imageInfoMapper.findImageInfoByLshAndBusinessTypeId(lsh,businessTypeId);
        if(imageInfo==null){
            return new ArrayList<>();
        }else{
            //根据imageId找到所有图片
            List<ImagePhotoRel> imagePhotoRelList=imagePhotoRelMapper.getImagePhotoRelList(imageInfo.getImageId());
            if(imagePhotoRelList!=null&&imagePhotoRelList.size()>0){
                return imagePhotoRelList;
            }else{
                return new ArrayList<>();
            }
        }
    }

    @Override
    public void delImagePhotoRelByLshCodeAndPhotoCode(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh, @ThriftField(value = 2, name = "photoCode", requiredness = ThriftField.Requiredness.NONE) String photoCode) throws TException {
        ImageInfo imageInfo=imageInfoMapper.findImageInfoByLsh(lsh);
        if(imageInfo!=null&&imageInfo.getImageId()!=0){
            imagePhotoRelMapper.delImagePhotoRelByImageIdAndPhotoCode(imageInfo.getImageId(),photoCode);
        }
    }

    @Override
    public int countImagePhoto() throws TException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
       MinAndMaxId minAndMaxId = imageInfoMapper.findImageIdIsMinAndMaxByDate(sdf.format(date)+" 00:00:00",sdf.format(date)+" 23:59:59");
        if(minAndMaxId!=null){
            return imagePhotoRelMapper.countImagePhoto(minAndMaxId.getMinId(),minAndMaxId.getMaxId());
        }
        return 0;
    }

}
