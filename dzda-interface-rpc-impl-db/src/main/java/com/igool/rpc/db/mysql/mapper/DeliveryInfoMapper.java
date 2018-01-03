package com.igool.rpc.db.mysql.mapper;


import com.igool.rpc.db.model.thrift.DeliveryInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DeliveryInfoMapper {


    String getMaxDeliveryId();

    void addDeliveryInfo(@Param("deliveryInfo") DeliveryInfo deliveryInfo);

    void addDeliveryInfoRel(@Param("deliveryId") int deliveryId, @Param("businessIds") List<Integer> businessIds);

    DeliveryInfo findDeliveryInfoByPcCode(@Param("pcCode")String pcCode);

    List<DeliveryInfo> findDeliveryListByPcCode(@Param("pcCode")String pcCode);

    /**
     * 根据查询条件查B转A签收数据
     * @param pcCode
     * @param startTime
     * @param endTime
     * @param rowBounds
     * @return
     */
    List<DeliveryInfo> getDeliveryListBza(@Param("pcCode") String pcCode, @Param("startTime") String startTime, @Param("endTime")String endTime, RowBounds rowBounds);

    /**
     * 根据查询条件查B转A签收数据条数
     * @param pcCode
     * @param startTime
     * @param endTime
     * @param rowBounds
     * @return
     */
    long getDeliveryListCountBza(@Param("pcCode") String pcCode, @Param("startTime") String startTime, @Param("endTime")String endTime, RowBounds rowBounds);

    /**
     * 根据批次号得到登记数
     * @param pcCode
     * @return
     */
    int getDjCountByPcCode(@Param("pcCode") String pcCode);

    List<DeliveryInfo> getJjlxByCodes(@Param("deliveryinfos")List<DeliveryInfo> deliveryinfos);

    List<DeliveryInfo> ajaxLookSignDetailByCode(@Param("code")String code);

    void updateJjzt(@Param("deliveryId")String deliveryId,@Param("type")int type);

    void delDeliveryRel(@Param("ids") String[] str, @Param("deliveryId")String deliveryId);

    List<DeliveryInfo> findRegisterNumberByCode(@Param("deliveryinfos")List<DeliveryInfo> deliveryinfos);
    void delDeliveryByPcCode(@Param("pcCode")String pcCode);

    List<DeliveryInfo> getDeliveryListByUserPurview(@Param("code")String code,@Param("userId") int userId, @Param("startTime")String startTime,
                                                    @Param("endTime")String endTime,@Param("isAdmin") boolean isAdmin, RowBounds rowBounds);

    int getDeliveryListByUserPurviewCount(@Param("code")String code,@Param("userId") int userId, @Param("startTime")String startTime,
                                          @Param("endTime")String endTime,@Param("isAdmin") boolean isAdmin);

    List<DeliveryInfo> findDeliveryByCodeAndDate(@Param("code")String code, @Param("start")String start, @Param("end")String end, RowBounds rowBounds);

    long findDeliveryByCodeAndDateCount(@Param("code")String code, @Param("start")String start, @Param("end")String end);

}
