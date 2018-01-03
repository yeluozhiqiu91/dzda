package com.igool.rpc.db.oracle.mapper;

import com.igool.rpc.db.model.thrift.VehicleDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang on 2017/11/15.
 */
@Repository
public interface SynVehicleMapper {
    public List<VehicleDetail> findVehicleDetailsBySfz(@Param("sfzs") String sfzs, @Param("hphms")String hphms, @Param("hpzls")String hpzls);
}
