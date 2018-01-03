package com.igool.rpc.db.service.impl;

import com.igool.rpc.db.model.thrift.BusinessTypeInfo;
import com.igool.rpc.db.model.thrift.PlateType;
import com.igool.rpc.db.service.thrift.BusinessTypeService;
import com.igool.rpc.db.service.thrift.CacheService;
import com.igool.rpc.db.service.thrift.PlateTypeService;
import com.stnts.common.util.collection.Collections3;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheServiceImpl implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    private  static final  String MAP_KEY = "map.key";
    private static final String BUSINESS_TYPE_KEY = "business.type.key";
    private static final String PLATE_TYPE_KEY = "plate.type.key";
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BusinessTypeService businessTypeService;
    @Autowired
    PlateTypeService plateTypeService;
    @Override
    public void close() {
        this.close();
    }

    @PostConstruct
    private void init(){
        try {
            //缓存初始化
            //businesstype
            logger.info("start init businesstype ");
            Map<String,String> businessTypeMap = loadBusinessType();
            Map<String,String> plateTypeMap = loadPlateTypeService();
            redisTemplate.delete(MAP_KEY);
            //缓存时间为一天
            redisTemplate.expire(MAP_KEY, 1, TimeUnit.DAYS);
            logger.info("set cache expire success! ");
            redisTemplate.boundHashOps(MAP_KEY).put(BUSINESS_TYPE_KEY, businessTypeMap);
            redisTemplate.boundHashOps(MAP_KEY).put(PLATE_TYPE_KEY, plateTypeMap);
            //
        }catch ( TException e){
            logger.error("init cache error {}",e);
        }catch (Exception e){
            logger.error("redis occur error {}",e);
            //redisTemplate.delete();
        }
    }

    /**从数据库加载**/
    private Map<String,String> loadBusinessType() throws TException {
        List<BusinessTypeInfo> businessTypeInfos = businessTypeService.getAllBusinessType();
        Map<String,String> businessTypeMap = Collections3.extractToMap(businessTypeInfos,"businessType", "name");
        return businessTypeMap;
    }

    /**从数据库加载号牌种类**/
    private Map<String,String> loadPlateTypeService() throws TException {
        List<PlateType> platTypeInfos = plateTypeService.getAllPlateType();
        Map<String,String> plateTypeMap = Collections3.extractToMap(platTypeInfos,"plateType", "name");
        return plateTypeMap;
    }
    /**
     * 得到业务类型映射表
     * **/
    @Override
    public Map<String, String> getBusinessType() throws TException {
        Map<String,String> businessTypeMap = (Map<String, String>) redisTemplate.boundHashOps(MAP_KEY).get(BUSINESS_TYPE_KEY);
        if ( businessTypeMap == null || businessTypeMap.isEmpty()){
            logger.info("businesstype缓存失效，加载数据库");
            redisTemplate.boundHashOps(MAP_KEY).put(BUSINESS_TYPE_KEY, businessTypeMap);
            return loadBusinessType();
        }
        return businessTypeMap;
    }

    /**
     * 得到号版种类映射表
     * **/
    @Override
    public Map<String, String> getPlatType() throws TException {
        Map<String,String> plateTypeMap = (Map<String, String>) redisTemplate.boundHashOps(MAP_KEY).get(PLATE_TYPE_KEY);
        if ( plateTypeMap == null || plateTypeMap.isEmpty()){
            logger.info("plateTypeMap缓存失效，加载数据库");
            redisTemplate.boundHashOps(MAP_KEY).put(PLATE_TYPE_KEY, plateTypeMap);
            return loadPlateTypeService();
        }
        return plateTypeMap;
    }
}
