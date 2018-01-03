package com.igool.rpc.db.service.impl;

import com.igool.rpc.db.mysql.mapper.PlateTypeMapper;
import com.igool.rpc.db.model.thrift.PlateType;
import com.igool.rpc.db.service.thrift.PlateTypeService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wang on 2017/9/25.
 */
public class PlateTypeServiceImpl implements PlateTypeService{
    @Autowired
    PlateTypeMapper plateTypeMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<PlateType> getAllPlateType() throws TException {
        return plateTypeMapper.getAllPlateType();
    }
}
