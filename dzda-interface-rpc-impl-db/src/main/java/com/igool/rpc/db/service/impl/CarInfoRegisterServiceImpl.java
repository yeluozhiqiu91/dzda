package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.model.thrift.FindRegister;
import com.igool.rpc.db.model.thrift.Vehicle;
import com.igool.rpc.db.mysql.mapper.CarInfoRegisterMapper;
import com.igool.rpc.db.oracle.mapper.SynchroLibraryMapper;
import com.igool.rpc.db.service.thrift.CarInfoRegisterService;
import com.igool.util.BizException;
import com.igool.util.ConstantsEnum;
import com.igool.util.ExceptionEnum;
import com.igool.util.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author heqf
 * @version 1.0
 * @date  2017-01-12 10:23:27
 */

public class CarInfoRegisterServiceImpl implements CarInfoRegisterService {

    protected static final Logger logger = LoggerFactory.getLogger(CarInfoRegisterServiceImpl.class);

    @Resource
    CarInfoRegisterMapper carInfoRegisterMapper;
    @Autowired
    SynchroLibraryMapper synchroLibraryMapper;

    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<Vehicle> findCarInfoBySfzAndName(@ThriftField(value = 1, name = "singleSfz", requiredness = ThriftField.Requiredness.NONE) String singleSfz, @ThriftField(value = 2, name = "name", requiredness = ThriftField.Requiredness.NONE) String name, @ThriftField(value = 3, name = "registertType", requiredness = ThriftField.Requiredness.NONE) String registertType) throws TException {
        logger.info("findCarInfoBySfzAndName - begin ,  singleSfz : " + singleSfz + "  name : " + name + "  registertType : " + registertType);
        if (registertType.equals("1")) {
            if (StringUtils.isEmpty(singleSfz) /*|| StringUtils.isEmpty(name)*/) {
                logger.warn(ExceptionEnum.参数异常.name());
                throw new BizException(ExceptionEnum.参数异常.getCode(), ExceptionEnum.参数异常.name());
            }
        } else if (registertType.equals("2")) {
            if (StringUtils.isEmpty(name)) {
                logger.warn(ExceptionEnum.参数异常.name());
                throw new BizException(ExceptionEnum.参数异常.getCode(), ExceptionEnum.参数异常.name());
            }
        }
        List<Vehicle> vehicles = synchroLibraryMapper.findCarInfoBySfzAndName(singleSfz==null?"":singleSfz,name==null?"":name);
        logger.info("findCarInfoBySfzAndName -- ,vehicles.size() : " + vehicles.size());
        if(vehicles.size()>0){
            logger.info("findCarInfoBySfzAndName  --  查询成功 ");
            return vehicles;
        }else {
            logger.warn("findCarInfoBySfzAndName  --  查询不到信息 ");
            throw new BizException(ExceptionEnum.查询不到信息.getCode(), ExceptionEnum.查询不到信息.name());
        }
    }

    @Override
    public void addAccredit(@ThriftField(value = 1, name = "findRegister", requiredness = ThriftField.Requiredness.NONE) FindRegister findRegister) throws TException {
        String[] hphmArr = findRegister.getHphm().split(",");
        String[] carSfzArr = findRegister.getCarSfz().split(",");
        String[] hpzlArr = findRegister.getHpzl().split(",");
        List<FindRegister> findRegisterList = new ArrayList<>();
        for(int i=0;i<carSfzArr.length;i++){
            FindRegister fr = new FindRegister();
            fr.setHphm(hphmArr[i]);
            fr.setHpzl(ConstantsEnum.Hpzl.getHpzlByTypeName(hpzlArr[i]).getValue());
            fr.setUnitName(findRegister.getUnitName());
            fr.setType(findRegister.getType());
            fr.setCarSfz(carSfzArr[i]);
            fr.setLetSfz(findRegister.getLetSfz());
            fr.setUserId(findRegister.getUserId());
            findRegisterList.add(fr);
        }
        carInfoRegisterMapper.addAccredit(findRegisterList);
    }

    @Override
    public List<FindRegister> findCarInfoByVehicles(@ThriftField(value = 1, name = "vehicles", requiredness = ThriftField.Requiredness.NONE) List<com.igool.rpc.db.model.thrift.Vehicle> vehicles, @ThriftField(value = 2, name = "singleSfz", requiredness = ThriftField.Requiredness.NONE) String singleSfz) throws TException {
        logger.info("findCarInfoByVehicles - begin ");
        List<FindRegister> findRegisters = carInfoRegisterMapper.findCarInfoByCarCodeAndSfz(singleSfz);
        logger.info("findCarInfoByVehicles - end ");
        return findRegisters;
    }

    @Override
    public List<FindRegister> findCarInfoByFindRegister(@ThriftField(value = 1, name = "accreditSfz", requiredness = ThriftField.Requiredness.NONE) String accreditSfz, @ThriftField(value = 2, name = "hphms", requiredness = ThriftField.Requiredness.NONE) String hphms, @ThriftField(value = 3, name = "carSfzs", requiredness = ThriftField.Requiredness.NONE) String carSfzs, @ThriftField(value = 4, name = "hpzls", requiredness = ThriftField.Requiredness.NONE) String hpzls) throws TException {
        String[] hphmArr = hphms.split(",");
        String[] carSfzArr = carSfzs.split(",");
        String[] hpzlArr = hpzls.split(",");
        List<FindRegister> findRegisters = carInfoRegisterMapper.findCarInfoByFindRegister(accreditSfz);
        List<FindRegister> findRegisterList = new ArrayList<>();
        for(FindRegister f : findRegisters){
            for( int i=0;i<hphmArr.length;i++ ){
                if(     f.getHphm().equals(hphmArr[i]) &&
                        f.getHpzl().equals(ConstantsEnum.Hpzl.getHpzlByTypeName(hpzlArr[i]).getValue()) &&
                        f.getCarSfz().equals(carSfzArr[i])){
                    FindRegister findRegister = new FindRegister();
                    findRegister.setLetSfz(f.getLetSfz());
                    findRegister.setHphm(f.getHphm());
                    findRegisterList.add(findRegister);
                }
            }
        }

        return findRegisterList;
    }

}
