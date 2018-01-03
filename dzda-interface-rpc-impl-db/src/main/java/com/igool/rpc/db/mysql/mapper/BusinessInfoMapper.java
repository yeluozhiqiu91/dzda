package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by wang on 2017/9/25.
 */
public interface BusinessInfoMapper {

    BusinessInfo findBusinessInfoByCode(String code);


    void addBusinessInfo(List<BusinessInfo> businessInfo);

    void updateBusinessInfoWzAndZt(@Param("businessInfosUpd") List<BusinessInfo> businessInfosUpd,@Param("wz") int wz, @Param("zt")int zt, @Param("box")String box);
    List<BusinessInfo> getBusinessInfoList(@Param("businessInfo") BusinessInfo businessInfo, RowBounds rowBounds);
    Long getBusinessInfoListCount(@Param("businessInfo")BusinessInfo businessInfo);
    List<BusinessInfo> getBusinessInfoListForShow(@Param("businessInfo")BusinessInfo businessInfo,RowBounds rowBounds);
    Long getBusinessInfoListForShowCount(@Param("businessInfo")BusinessInfo businessInfo);

    List<Wtda> getWtdaListByQszl(@Param("startTime") String startTime, @Param("endTime") String endTime,RowBounds rowBounds);
    List<Wtda> getWtdaListByQsls(@Param("startTime") String startTime, @Param("endTime") String endTime,RowBounds rowBounds);
    long getWtdaListByQszlCount(@Param("startTime") String startTime, @Param("endTime") String endTime);
    long getWtdaListByQslsCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Jjtj> getDeliveryList(@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<Jjtj> getDeliveryAndSignList(@Param("startTime") String startTime,@Param("endTime") String endTime,RowBounds rowBounds);
    long getDeliveryAndSignListCount(@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<Jjtj> getSignList(@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<Jjtj> getOldDeliveryList(@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<Jjtj> getOldSignList(@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 根据时间查流水明细，旧数据,交接类型为登记，查登记表
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getOldLsmxDeliveryList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    /**
     * 查流水明细，新数据，交接类型为登记
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getNewLsmxDeliveryList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx, RowBounds rowBounds);

    /**
     * 根据时间查流水明细，旧数据,交接类型为签收，查签收表
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getOldLsmxSignList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    /**
     * 根据时间查档案明细，旧数据,交接类型为登记，查登记表
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getOldDamxDeliveryList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    /**
     * 旧数据，档案签收明细，签收表
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getOldDamxSignList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    /**
     * 查档案明细，新数据，交接类型为登记
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     */
    List<Lsmx> getNewDamxDeliveryList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx,RowBounds rowBounds);

    List<Lsmx> getNewLsmxSignList(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("jjlx") int jjlx,RowBounds rowBounds);

    List<Lsmx> getNewDamxSignList(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("jjlx") int jjlx,RowBounds rowBounds);

    BusinessInfo findNativeBusinessInfoByLsh(@Param("lsh") String lsh);

    List<BusinessInfo> findNativeBusinessInfoByDeliveryPcCode(@Param("pcCode") String pcCode);

    /**
     * 根据批次号和流水状态查流水
     * @param pcCode
     * @param lszt
     * @return
     */
    List<BusinessInfo> findNativeBusinessInfoByDeliveryPcCodeAndLszt(@Param("pcCode") String pcCode,@Param("lszt") int lszt);

    void updateBusinessInfoWzAndZtById(@Param("businessId") int businessId,@Param("lszt") int lszt,@Param("lswz") int lswz);

    /**
     * 将流水保存到本地
     * @param businessInfo
     * @return
     */
    int saveBusinessInfo(@Param("businessInfo") BusinessInfo businessInfo);

    List<BusinessInfo> ajaxLookSignDetailByCode(@Param("code") String code, @Param("lszt1")int lszt1, @Param("lszt2")int lszt2);

    String findBoxByDeliveryIdAndLsh(@Param("deliveryId")String deliveryId);

    void updateWzAndZt(@Param("businessId") int businessId, @Param("wz")int wz,@Param("zt")int zt);

    void updateBox(@Param("box")String box, @Param("beforeBox")String beforeBox);

    void updateZt(@Param("businessId")int businessId, @Param("zt")int zt);

    void updateWz(@Param("signingId")String signingId, @Param("wz")int wz);

    List<BusinessInfo> getBusinessInfoListByBox(@Param("box")String box);

    List<BusinessInfo> getBusinessInfoListById(@Param("ids")String[] str);

    void updateBusinessInfosZt(@Param("ids")List<Integer> ids, @Param("zt")int aToBDj);

    long getBusinessInfoListForImageCount(@Param("businessInfo")BusinessInfo businessInfo, RowBounds rowBounds);

    List<BusinessInfo> getBusinessInfoListForImage(@Param("businessInfo")BusinessInfo businessInfo);

    List<BusinessInfo> findBusinessInfoByFileCode(@Param("fileId")String fileId);

    void updateQsStatus(@Param("qsStatus")int qsStatus, @Param("businessId")String businessId);

    int countQsStatusByFileId(@Param("fileId")String fileId, @Param("qsStatus1") int qsBusiness, @Param("qsStatus2") int zlQsBusiness);

    List<ImageInfo> findBusinessInfoByCodes(@Param("imageInfos")List<ImageInfo> imageInfos);

    List<BusinessInfo> getImageFileCodeDetail(@Param("imageInfos")List<ImageInfo> imageInfos);


    List<BusinessInfo> getBusinessInfoListByDeliveryId(@Param("deliveryId")String deliveryId);

    void deliveryBToA(@Param("businessInfosUpd") List<BusinessInfo> businessInfosUpd,@Param("wz") int wz, @Param("zt")int zt);

    List<BusinessInfo> ajaxLookSuccessSignDetailByCode(@Param("code")String code,@Param("lszt")int lszt);

    List<BusinessInfo> getBusinessInfoListByDeliveryCode(@Param("code")String deliveryCode);

    List<BusinessImageDTO> findBusinessIdAndImageIdAndBusinessTypeIdByLsh(@Param("lsh") String lsh);

    void deleteBusinessQszlRelByBusinessId(@Param("businessId") int businessId);

    void addBusinessQszlRels(@Param("rels") List<BusinessQszlRel> rels);

    FileInfo findFileInfoByFileCode(@Param("fileCode")String fileCode);

    List<Jjtj> getJxrwSignList(@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<BusinessInfo> findFileInfoByBox(@Param("box")String box);

    String findBusinessIdByCode(@Param("code")String code);

    List<String> findNotSignCodeByBox(@Param("box")String box);
    List<ImageInfo> getImageInfoListByCode(@Param("code") List<String> codeList);

    long getNewLsmxDeliveryListCount(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    long getNewLsmxSignListCount(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    long getNewDamxDeliveryListCount(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    long getNewDamxSignListCount(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("jjlx") int jjlx);

    List<BusinessInfo> getBusinessInfoListByCode(@Param("codeList") List<String> codeList);
}
