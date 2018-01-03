package com.igool.dzda.server.impl;

import com.igool.dzda.mapper.ImagePhotoRelPastMapper;
import com.igool.dzda.server.ImagePhotoRelPastService;
import com.igool.dzda.util.DynamicDataSource;
import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author H J .
 * @date 2017/8/30.
 */
public class ImagePhotoRelPastServiceImpl implements ImagePhotoRelPastService {

    @Autowired
    ImagePhotoRelPastMapper imagePhotoRelPastMapper;

    @Override
    public List<ImagePhotoRel> getImagePhotoRel(String i, int indexStart, int indexEnd) {

        DynamicDataSource.dataSource = i;
        return imagePhotoRelPastMapper.getImagePhotoRel(indexStart,indexEnd);
    }

    public ImagePhotoRelPastMapper getImagePhotoRelPastMapper() {
        return imagePhotoRelPastMapper;
    }

    public void setImagePhotoRelPastMapper(ImagePhotoRelPastMapper imagePhotoRelPastMapper) {
        this.imagePhotoRelPastMapper = imagePhotoRelPastMapper;
    }
}
