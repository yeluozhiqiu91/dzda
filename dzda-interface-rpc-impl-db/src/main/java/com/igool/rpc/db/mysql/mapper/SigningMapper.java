package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.DeliveryInfo;
import com.igool.rpc.db.model.thrift.Signing;
import com.igool.rpc.db.model.thrift.SigningRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface SigningMapper {


    void addSigning(@Param("signing") Signing signing);

    /**
     * 添加签收记录，返回ID
     * @param signing
     * @return
     */
    int saveSigning(@Param("signing") Signing signing);

    /**
     * 添加签收明细记录
     * @param signingRel
     */
    void saveSigningRel(@Param("signingRel") SigningRel signingRel);

    /**
     * 根据批次号找签收记录
     * @param pcCode
     * @return
     */
    Signing findSigningByPcCode(@Param("pcCode")String pcCode);

    /**
     * 根据批次号找签收明细条数
     * @param pcCode
     * @return
     */
    int getQsCountByPcCode(@Param("pcCode") String pcCode);

    /**
     * 跟新批次状态
     * @param signing
     */
    void updateSigingStatus(@Param("signing") Signing signing);

    String findBox(String box);

    void addSigningRef(@Param("businessInfo") BusinessInfo businessInfo,@Param("userId") int userId);

    int getBoxJjqk(@Param("box")String box, @Param("deliveryId")String deliveryId);

    void updateJjzt(@Param("signingId")String signingId,@Param("type")int type,@Param("userId") int userId,@Param("status") int status);

    void updateBox(@Param("box")String box, @Param("beforeBox")String beforeBox);

    List<Signing> findSignNumberByCode(@Param("deliveryinfos")List<DeliveryInfo> deliveryinfos);

    List<SigningRel> findSigningRelBySigningId(@Param("signingId")int signingId);

    String findSigningIdByDeliveryCode(@Param("deliveryCode")String deliveryCode);

    List<Integer> getBoxSigningState(@Param("signingId")String signingId, @Param("deliveryId")String deliveryId);

    void delSigningByPcCode(@Param("pcCode")String pcCode);

    List<Signing> findSigningByPcCodes(@Param("deliveryInfos")List<DeliveryInfo> deliveryInfos);

    String findPcCodeByBox(@Param("box")String box);
}
