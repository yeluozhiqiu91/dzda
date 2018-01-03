package com.igool.rpc.db.mapper;

import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by igool on 2017/8/28.
 */
public interface ImagePhotoRelMapper {
    void insert(ImagePhotoRel imagePhotoRel);
    List<ImagePhotoRel> getImagePhotoRelList(@Param("imageId") int imageId);

    void delImagePhotoRelByImageIdAndPhotoCode(@Param("imageId") int imageId, @Param("photoCode") String photoCode);

    List<ImagePhotoRel> findImagePhotoRelByImageIdAndOrderNum(@Param("imageInfoId") Integer imageInfoId, @Param("orderNum") Integer orderNum);

    List<ImagePhotoRel> findImagePhotoRelByImageIdAndPhotoCode(@Param("imageInfoId") Integer imageInfoId, @Param("photoCode") String photoCode);

    void updateImagePhotoRel(ImagePhotoRel imagePhotoRel);

    void addImagePhotoRel(ImagePhotoRel imagePhotoRel);

    int countImagePhoto(@Param("startId")int startId, @Param("endId")int endId);
}
