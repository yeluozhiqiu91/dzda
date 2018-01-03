package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.ImageInfo;
import com.igool.rpc.db.model.thrift.MinAndMaxId;
import com.igool.rpc.db.model.thrift.UserImageCount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/10/13.
 */
public interface ImageInfoMapper {
    /**
     * 通过流水号和业务类型id找到图片记录
     * @param lsh
     * @param businessTypeId
     * @return
     */
    ImageInfo findImageInfoByLshAndBusinessTypeId(@Param("lsh") String lsh, @Param("businessTypeId") int businessTypeId);

    /**
     * 通过流水号找到图片记录
     * @param lsh
     * @return
     */
    ImageInfo findImageInfoByLsh(@Param("lsh")String lsh);

    int addImageInfo(@Param("imageInfo") ImageInfo imageInfo);

    void updataImageInfo(@Param("imageInfo") ImageInfo imageInfo);

    int findDriverImageCountByDate(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("type")String type);

    List<Map<String,String>> findBusinessImageCount(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("type")String type);

    List<ImageInfo> streamNumByDate(@Param("startTime")String startTime, @Param("endTime")String endTime);

    List<ImageInfo> findImageCodeByDate(@Param("startTime")String startTime, @Param("endTime")String endTime);

    List<UserImageCount> userImageCountDriverNum(@Param("startTime")String startTime, @Param("endTime")String endTime);

    List<UserImageCount> userImageCountBusinessNum(@Param("startTime")String startTime, @Param("endTime")String endTime);

    List<ImageInfo> businessNumDetailByDateAndUserId(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("userId")String userId);

    List<ImageInfo> findImageCodeByDateAndUserId(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("userId")String userId);

    /**
     * 根据多个流水号查影像记录
     * @param lshs
     * @return
     */
    List<ImageInfo> findAllImageInfoByLshs(@Param("lshs") String lshs);

    MinAndMaxId findImageIdIsMinAndMaxByDate(@Param("startTime")String startTime, @Param("endTime")String endTime);

    String findImagePhotoNumByDate(@Param("startTime")String startTime, @Param("endTime")String endTime);
    List<ImageInfo> getImageInfoListByCode(@Param("codeList") List<String> codeList,RowBounds rowBounds);

    long getImageInfoListCountByCode(@Param("codeList") List<String> codeList);
}
