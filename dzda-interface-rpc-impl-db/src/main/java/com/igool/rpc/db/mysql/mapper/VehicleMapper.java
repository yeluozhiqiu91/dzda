package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.FindRegister;
import com.igool.rpc.db.model.thrift.VehicleOperator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang on 2017/11/16.
 */
@Repository
public interface VehicleMapper {
    void addVehicleOperator(VehicleOperator vehicleoperator);
    List<FindRegister> getFindRegisterByTypeAndStr(@Param("str") String str);
}
