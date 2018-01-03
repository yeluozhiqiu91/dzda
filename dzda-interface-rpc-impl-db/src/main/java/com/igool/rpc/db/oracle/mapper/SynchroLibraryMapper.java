package com.igool.rpc.db.oracle.mapper;


import com.igool.common.entity.VehFlow;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SynchroLibraryMapper {

    BusinessInfo getSynchroLibraryVehFlow(String lsh);

    BusinessInfo getSynchroLibraryVehFlowHis(String lsh);

    List<com.igool.rpc.db.model.thrift.VehicleFlow> findLsByDabh(String dabh);
    List<com.igool.rpc.db.model.thrift.VehicleFlow> findLsHisByDabh(String dabh);


    List<Vehicle> findCarInfoBySfzAndName(@Param("sfz")String sfz, @Param("name")String name);
}
