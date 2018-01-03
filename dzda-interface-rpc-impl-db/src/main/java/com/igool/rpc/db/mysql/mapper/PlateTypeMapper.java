package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.PlateType;

import java.util.List;

/**
 * Created by wang on 2017/9/25.
 */
public interface PlateTypeMapper {
    List<PlateType> getAllPlateType();
}
