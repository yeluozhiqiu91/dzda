package com.igool.test.dzda.service.thrift;

import com.igool.test.dzda.service.thrift.model.ImagePhotoRel;

import java.util.List;

/**
 * Created by igool on 2017/8/28.
 */
public interface ImagePhotoRefService {
    void insert(ImagePhotoRel model);
    List<ImagePhotoRel> getImagePhotoRelList(int imageId);
}
