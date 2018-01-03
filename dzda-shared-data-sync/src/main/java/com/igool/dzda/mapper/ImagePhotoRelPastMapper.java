package com.igool.dzda.mapper;

import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author H J .
 * @date 2017/8/30.
 */
public interface ImagePhotoRelPastMapper {

    List<ImagePhotoRel> getImagePhotoRel(@Param("indexStart") int indexStart, @Param("indexEnd")int indexEnd);

}
