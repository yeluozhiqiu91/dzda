package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.BusinessTypeDetail;
import com.igool.rpc.db.model.thrift.BusinessTypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wang on 2017/9/25.
 */
public interface BusinessTypeMapper {
    public List<BusinessTypeInfo> getAllBusinessType();

    /**
     * 根据业务类型找到业务类型数据
     * @param ywlx
     * @return
     */
    BusinessTypeInfo findBusinessTypeByYwlx(@Param("ywlx") String ywlx);

    List<BusinessTypeDetail> findBusinessTypeDetailByTypeId(@Param("businessTypeId") int businessTypeId);


    List<BusinessTypeDetail> findBusinessTypeDetailByBusinessTypeIdAndIsNecessity(@Param("businessTypeId") int businessTypeId, @Param("isNecessity") Integer isNecessity);

    /**
     * 通过流水号找业务类型
     * @param lsh
     * @return
     */
    BusinessTypeInfo findBusinessTypeByLsh(String lsh);

    List<BusinessTypeDetail> getBusinessTypeDetailByBusinessType(@Param("businessId") String businessId, @Param("businessType")String businessType);

    void deleteBusinessQsZl(@Param("businessId")String businessId);

    void updateQsZl(@Param("businessId")String businessId, @Param("businessDetailIdList")String[] businessDetailIdList,@Param("userId")int userId);
    List<BusinessTypeDetail> findBusinessTypeDetailByBstIdAndIsSm(@Param("businessTypeId")int businessTypeId,@Param("isSm") Integer isSm);
}
