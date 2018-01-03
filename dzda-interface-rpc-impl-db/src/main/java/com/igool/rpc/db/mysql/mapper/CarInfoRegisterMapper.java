package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.FindRegister;
import com.igool.rpc.db.model.thrift.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author H J .
 * @date 2017/11/23.
 */
public interface CarInfoRegisterMapper {

    List<FindRegister> findCarInfoByCarCodeAndSfz(@Param("singleSfz")String singleSfz);

    List<FindRegister> findCarInfoByFindRegister(@Param("accreditSfz")String accreditSfz);

    void addAccredit(@Param("findRegisters")List<FindRegister> findRegisterList);
}
