package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mapper.ImagePhotoRelMapper;
import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.mysql.mapper.*;
import com.igool.rpc.db.oracle.mapper.SynchroLibraryMapper;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.CacheService;
import com.igool.util.ConstantUtil;
import com.igool.util.ConstantsEnum;
import com.stnts.common.util.collection.Collections3;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang on 2017/9/25.
 */
public class BusinessInfoServiceImpl implements BusinessInfoService {
    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    SynchroLibraryMapper synchroLibraryMapper;

    @Autowired
    SigningMapper signingMapper;
    @Autowired
    DeliveryInfoMapper deliveryInfoMapper;

    @Autowired
    FileInfoMapper fileInfoMapper;

    @Autowired
    BusinessTypeMapper businessTypeMapper;

    @Autowired
    ImagePhotoRelMapper imagePhotoRelMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    CacheService cacheService;
    @Autowired
    ImageInfoMapper imageInfoMapper;
    Logger logger = LoggerFactory.getLogger(BusinessInfoServiceImpl.class);
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void close() {
        this.close();
    }

    @Override
    public BusinessInfo findBusinessInfoByCode(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code) throws TException {
        logger.info(" findBusinessInfoByCode -begin  code : " + code);
        if (code.length() == 11) {
            code = code.substring(1, 11);
            code = "1A" + code + 0;
        }
        BusinessInfo businessInfo = businessInfoMapper.findBusinessInfoByCode(code);
        if (businessInfo == null) {
            logger.info(" findBusinessInfoByCode -- 查询同步库   ");

            businessInfo = synchroLibraryMapper.getSynchroLibraryVehFlow(code);
            if (businessInfo == null) {
                businessInfo = synchroLibraryMapper.getSynchroLibraryVehFlowHis(code);
            }
        }
        if (businessInfo != null) {
            businessInfo.setPlateTypeStr(ConstantsEnum.Hpzl.getHpzlByType(businessInfo.getPlateType()).name());
        }
        logger.info(" findBusinessInfoByCode -end ");
        return businessInfo == null ? new BusinessInfo() : businessInfo;
    }

    @Override
    public void deleteBusinessInfo(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo) throws TException {

    }

    @Override
    public List<BusinessInfo> getBusinessInfoList(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return businessInfoMapper.getBusinessInfoList(businessInfo, new RowBounds(startIndex, pageSize));
    }

    @Override
    public long getBusinessInfoListCount(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return businessInfoMapper.getBusinessInfoListCount(businessInfo);
    }

    @Override
    public List<BusinessInfo> getBusinessInfoListForShow(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<BusinessInfo> list=businessInfoMapper.getBusinessInfoListForShow(businessInfo, new RowBounds(startIndex, pageSize));
        Map<String, String> mb=cacheService.getBusinessType();
        Map<String, String> mp=cacheService.getPlatType();
        for(BusinessInfo b:list){
            b.setBusinessTypeStr(mb.get(b.getBusinessType()));
            b.setPlateTypeStr(mp.get(b.getPlateType()));
        }
        return list;
    }

    @Override
    public long getBusinessInfoListForShowCount(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        long a=businessInfoMapper.getBusinessInfoListForShowCount(businessInfo);
        return a;
    }

    @Override
    public List<Wtda> getWtdaList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 4, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        long qszlCount = businessInfoMapper.getWtdaListByQszlCount(startTime, endTime);
        long qslsCount = businessInfoMapper.getWtdaListByQslsCount(startTime, endTime);
        if (startIndex + pageSize <= qszlCount) {
            return businessInfoMapper.getWtdaListByQszl(startTime, endTime, new RowBounds(startIndex, pageSize));
        } else if (startIndex < qszlCount && startIndex + pageSize > qszlCount) {
            List<Wtda> wtdaList = new ArrayList<>();
            List<Wtda> qszlList = businessInfoMapper.getWtdaListByQszl(startTime, endTime, new RowBounds(startIndex, pageSize));
            List<Wtda> qslsList = businessInfoMapper.getWtdaListByQsls(startTime, endTime, new RowBounds(0, startIndex + pageSize - qszlList.size()));
            wtdaList.addAll(qszlList);
            wtdaList.addAll(qslsList);
            return wtdaList;
        } else if (startIndex >= qszlCount) {
            List<Wtda> qslsList = businessInfoMapper.getWtdaListByQsls(startTime, endTime, new RowBounds((int) (startIndex - qszlCount), pageSize));
            return qslsList;
        } else {
            return new ArrayList<>();
        }

        /*List<Wtda> qszlList=businessInfoMapper.getWtdaListByQszl(startTime,endTime);
        List<Wtda> qslsList=businessInfoMapper.getWtdaListByQsls(startTime,endTime);*/
    }

    @Override
    public long getWtdaListCount(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 4, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        long qszlCount = businessInfoMapper.getWtdaListByQszlCount(startTime, endTime);
        long qslsCount = businessInfoMapper.getWtdaListByQslsCount(startTime, endTime);
        return qslsCount + qszlCount;
    }

    @Override
    public List<Jjtj> getDeliveryList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getDeliveryList(startTime, endTime);
        if (jjtjList != null && jjtjList.size() > 0) {
            for (Jjtj jjtj : jjtjList) {
                if (jjtj.getJjlx() == 1) {
                    jjtj.setDepartmentName("武汉市车管所总所");
                    jjtj.setJjlxStr("A库转B库登记");
                } else if (jjtj.getJjlx() == 3) {
                    jjtj.setDepartmentName("金信融威");
                    jjtj.setJjlxStr("B库转A库登记");
                }
            }
            return jjtjList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Jjtj> getDeliveryAndSignList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 4, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getDeliveryAndSignList(startTime, endTime,new RowBounds(startIndex, pageSize));
        for (Jjtj jjtj : jjtjList) {
            if (jjtj.getJjlx() == 2) {
                jjtj.setDepartmentName("金信融威");
                jjtj.setJjlxStr("A库转B库签收");
            } else if (jjtj.getJjlx() == 4) {
                jjtj.setDepartmentName("武汉市车管所总所");
                jjtj.setJjlxStr("B库转A库签收");
            }else if (jjtj.getJjlx() == 1) {
                jjtj.setDepartmentName("武汉市车管所总所");
                jjtj.setJjlxStr("A库转B库登记");
            }else if (jjtj.getJjlx() == 3) {
                jjtj.setDepartmentName("金信融威");
                jjtj.setJjlxStr("B库转A库登记");
            }
        }
        return jjtjList;
    }
    @Override
    public long getDeliveryAndSignListCount(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 4, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return  businessInfoMapper.getDeliveryAndSignListCount(startTime, endTime);

    }

    @Override
    public List<Jjtj> getSignList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getSignList(startTime, endTime);
        if (jjtjList != null && jjtjList.size() > 0) {
            for (Jjtj jjtj : jjtjList) {
                if (jjtj.getJjlx() == 2) {
                    jjtj.setDepartmentName("金信融威");
                    jjtj.setJjlxStr("A库转B库签收");
                } else if (jjtj.getJjlx() == 4) {
                    jjtj.setDepartmentName("武汉市车管所总所");
                    jjtj.setJjlxStr("B库转A库签收");
                }
            }
            return jjtjList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Jjtj> getOldDeliveryList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getOldDeliveryList(startTime, endTime);
        if (jjtjList != null && jjtjList.size() > 0) {
            for (Jjtj jjtj : jjtjList) {
                if (jjtj.getJjlx() == 1) {
                    jjtj.setDepartmentName("武汉市车管所总所");
                    jjtj.setJjlxStr("A库转B库登记");
                } else if (jjtj.getJjlx() == 3) {
                    jjtj.setDepartmentName("金信融威");
                    jjtj.setJjlxStr("B库转A库登记");
                }
            }
            return jjtjList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Jjtj> getOldSignList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getOldSignList(startTime, endTime);
        if (jjtjList != null && jjtjList.size() > 0) {
            for (Jjtj jjtj : jjtjList) {
                if (jjtj.getJjlx() == 2) {
                    jjtj.setDepartmentName("金信融威");
                    jjtj.setJjlxStr("A库转B库签收");
                } else if (jjtj.getJjlx() == 4) {
                    jjtj.setDepartmentName("武汉市车管所总所");
                    jjtj.setJjlxStr("B库转A库签收");
                }
            }
            return jjtjList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Lsmx> getOldLsmxList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "jjlx", requiredness = ThriftField.Requiredness.NONE) int jjlx) throws TException {
        List<Lsmx> lsmxList = new ArrayList<>();
        if (jjlx == 1) {
            lsmxList = businessInfoMapper.getOldLsmxDeliveryList(startTime, endTime, jjlx);
        } else if (jjlx == 2) {
            lsmxList = businessInfoMapper.getOldLsmxSignList(startTime, endTime, jjlx);
        }
        if (lsmxList != null && lsmxList.size() > 0) {
            //查用户
            List<UserInfo> userInfoList = userInfoMapper.getAllUser();
            Map<Integer, String> userMap = new HashMap<>();
            for (UserInfo u : userInfoList) {
                userMap.put(u.getUserId(), u.getName());
            }
            for (Lsmx lsmx : lsmxList) {
                lsmx.setUserName(userMap.get(lsmx.getUserId()) == null ? "未知" : userMap.get(lsmx.getUserId()));
            }
            return lsmxList;
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Lsmx> getOldDamxList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "jjlx", requiredness = ThriftField.Requiredness.NONE) int jjlx) throws TException {
        List<Lsmx> lsmxList = new ArrayList<>();
        if (jjlx == 1) {
            lsmxList = businessInfoMapper.getOldDamxDeliveryList(startTime, endTime, jjlx);
        } else if (jjlx == 2) {
            lsmxList = businessInfoMapper.getOldDamxSignList(startTime, endTime, jjlx);
        }
        if (lsmxList != null && lsmxList.size() > 0) {
            //查用户
            List<UserInfo> userInfoList = userInfoMapper.getAllUser();
            Map<Integer, String> userMap = new HashMap<>();
            for (UserInfo u : userInfoList) {
                userMap.put(u.getUserId(), u.getName());
            }
            for (Lsmx lsmx : lsmxList) {
                lsmx.setUserName(userMap.get(lsmx.getUserId()) == null ? "未知" : userMap.get(lsmx.getUserId()));
            }
            return lsmxList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Lsmx> getNewLsmxList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "jjlx", requiredness = ThriftField.Requiredness.NONE) int jjlx, @ThriftField(value = 4, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 5, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<Lsmx> lsmxList = new ArrayList<>();
        if (jjlx == 1 || jjlx == 3) {
            lsmxList = businessInfoMapper.getNewLsmxDeliveryList(startTime, endTime, jjlx,new RowBounds(startIndex, pageSize));
            if(lsmxList!=null&&lsmxList.size()>0) {
                List<Integer> businessId = Collections3.extractToList(lsmxList, "businessId");
                String[] strs = new String[businessId.size()];
                for (int i = 0; i < businessId.size(); i++) {
                    strs[i] = String.valueOf(businessId.get(i));
                }
                List<BusinessInfo> businessInfoList = businessInfoMapper.getBusinessInfoListById(strs);
                Map<Integer, BusinessInfo> businessMap = Collections3.extractToKeyObjectMap(businessInfoList, "businessId");
                for (Lsmx lsmx : lsmxList) {
                    lsmx.setBox(businessMap.get(lsmx.getBusinessId()).getBox());
                    lsmx.setFileCode(businessMap.get(lsmx.getBusinessId()).getFileCode());
                    lsmx.setBusinessCode(businessMap.get(lsmx.getBusinessId()).getCode());
                }
            }
        } else if (jjlx == 2 || jjlx == 4) {
            lsmxList = businessInfoMapper.getNewLsmxSignList(startTime, endTime, jjlx,new RowBounds(startIndex, pageSize));
            if(lsmxList!=null&&lsmxList.size()>0) {
                List<Integer> businessId = Collections3.extractToList(lsmxList, "businessId");
                String[] strs = new String[businessId.size()];
                for (int i = 0; i < businessId.size(); i++) {
                    strs[i] = String.valueOf(businessId.get(i));
                }
                List<BusinessInfo> businessInfoList = businessInfoMapper.getBusinessInfoListById(strs);
                Map<Integer, BusinessInfo> businessMap = Collections3.extractToKeyObjectMap(businessInfoList, "businessId");
                for (Lsmx lsmx : lsmxList) {
                    lsmx.setBox(businessMap.get(lsmx.getBusinessId()).getBox());
                    lsmx.setFileCode(businessMap.get(lsmx.getBusinessId()).getFileCode());
                    lsmx.setBusinessCode(businessMap.get(lsmx.getBusinessId()).getCode());
                }
            }
        }
        if (lsmxList != null && lsmxList.size() > 0) {
            //查用户
            List<UserInfo> userInfoList = userInfoMapper.getAllUser();
            Map<Integer, String> userMap = new HashMap<>();
            for (UserInfo u : userInfoList) {
                userMap.put(u.getUserId(), u.getName());
            }
            for (Lsmx lsmx : lsmxList) {
                lsmx.setUserName(userMap.get(lsmx.getUserId()) == null ? "未知" : userMap.get(lsmx.getUserId()));
            }
            return lsmxList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public long getNewLsmxListCount(String startTime, String endTime, int jjlx, int startIndex, int pageSize) throws TException {
        long count = 0;
        if (jjlx == 1 || jjlx == 3) {
            count =  businessInfoMapper.getNewLsmxDeliveryListCount(startTime,endTime,jjlx);
        }else if (jjlx == 2 || jjlx == 4) {
            count =  businessInfoMapper.getNewLsmxSignListCount(startTime,endTime,jjlx);
        }
        return count;
    }

    @Override
    public List<Lsmx> getNewDamxList(@ThriftField(value = 1, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 2, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 3, name = "jjlx", requiredness = ThriftField.Requiredness.NONE) int jjlx, @ThriftField(value = 4, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 5, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<Lsmx> lsmxList = new ArrayList<>();
        if (jjlx == 1 || jjlx == 3) {
            lsmxList = businessInfoMapper.getNewDamxDeliveryList(startTime, endTime, jjlx,new RowBounds(startIndex, pageSize));
            if (lsmxList!=null&&lsmxList.size()>0) {
                List<Integer> businessId = Collections3.extractToList(lsmxList, "businessId");
                String[] strs = new String[businessId.size()];
                for (int i = 0; i < businessId.size(); i++) {
                    strs[i] = String.valueOf(businessId.get(i));
                }
                List<BusinessInfo> businessInfoList = businessInfoMapper.getBusinessInfoListById(strs);
                Map<Integer, BusinessInfo> businessMap = Collections3.extractToKeyObjectMap(businessInfoList, "businessId");
                for (Lsmx lsmx : lsmxList) {
                    lsmx.setBox(businessMap.get(lsmx.getBusinessId()).getBox());
                    lsmx.setFileCode(businessMap.get(lsmx.getBusinessId()).getFileCode());
                    lsmx.setBusinessCode(businessMap.get(lsmx.getBusinessId()).getCode());
                }
            }
        } else if (jjlx == 2 || jjlx == 4) {
            lsmxList = businessInfoMapper.getNewDamxSignList(startTime, endTime, jjlx,new RowBounds(startIndex, pageSize));
            if (lsmxList!=null&&lsmxList.size()>0) {
                List<Integer> businessId = Collections3.extractToList(lsmxList, "businessId");
                String[] strs = new String[businessId.size()];
                for (int i = 0; i < businessId.size(); i++) {
                    strs[i] = String.valueOf(businessId.get(i));
                }
                List<BusinessInfo> businessInfoList = businessInfoMapper.getBusinessInfoListById(strs);
                Map<Integer, BusinessInfo> businessMap = Collections3.extractToKeyObjectMap(businessInfoList, "businessId");
                for (Lsmx lsmx : lsmxList) {
                    lsmx.setBox(businessMap.get(lsmx.getBusinessId()).getBox());
                    lsmx.setFileCode(businessMap.get(lsmx.getBusinessId()).getFileCode());
                    lsmx.setBusinessCode(businessMap.get(lsmx.getBusinessId()).getCode());
                }
            }
        }
        if (lsmxList != null && lsmxList.size() > 0) {
            //查用户
            List<UserInfo> userInfoList = userInfoMapper.getAllUser();
            Map<Integer, String> userMap = new HashMap<>();
            for (UserInfo u : userInfoList) {
                userMap.put(u.getUserId(), u.getName());
            }
            for (Lsmx lsmx : lsmxList) {
                lsmx.setUserName(userMap.get(lsmx.getUserId()) == null ? "未知" : userMap.get(lsmx.getUserId()));
            }
            return lsmxList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public long getNewDamxListCount(String startTime, String endTime, int jjlx, int startIndex, int pageSize) throws TException {
        long count = 0;
        if (jjlx == 1 || jjlx == 3) {
            count =  businessInfoMapper.getNewDamxDeliveryListCount(startTime,endTime,jjlx);
        }else if (jjlx == 2 || jjlx == 4) {
            count =  businessInfoMapper.getNewDamxSignListCount(startTime,endTime,jjlx);
        }
        return count;
    }

    @Override
    public DeliveryInfo addAToBRegister(@ThriftField(value = 1, name = "businessInfos", requiredness = ThriftField.Requiredness.NONE) List<BusinessInfo> businessInfos, @ThriftField(value = 2, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 3, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId) throws TException {
        logger.info("addAToBRegister -begin  businessInfos.size() :" + businessInfos.size() + "  box :" + box + "  userId :" + userId);
        String i = signingMapper.findBox(box);
        if (i == null) {
            logger.info("addAToBRegister  -- 登记数量 : " + businessInfos.size());
            List<BusinessInfo> businessInfosAdd = new ArrayList<>();
            List<BusinessInfo> businessInfosUpd = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            for (BusinessInfo b : businessInfos) {
                b.setBox(box);
                b.setPlateType(b.getPlateType());
                if (b.getBusinessId() == 0) {
                    b.setLswz(ConstantUtil.A);
                    b.setLszt(ConstantUtil.A_TO_B_DJ);
                    b.setQsStatus(ConstantUtil.ZC_BUSINESS);
                    businessInfosAdd.add(b);
                } else {
                    businessInfosUpd.add(b);
                    integers.add(b.getBusinessId());
                }
            }
            logger.info("addAToBRegister  -- 新增流水 : " + businessInfosAdd.size() + "    -- 修改流水 : " + businessInfosUpd.size());
            if (businessInfosUpd.size() > 0) {
                businessInfoMapper.updateBusinessInfoWzAndZt(businessInfosUpd, ConstantUtil.A, ConstantUtil.A_TO_B_DJ, box);
                logger.info("addAToBRegister  -- 流水修改完成 ");
            }
            if (businessInfosAdd.size() > 0) {
                /*
                *  使用map集合去重复数据
                * */
                Map<String, BusinessInfo> map = new HashMap();
                for (int r = 0; r < businessInfosAdd.size(); r++) {
                    map.put(businessInfosAdd.get(r).getFileCode(), businessInfosAdd.get(r));
                }
                List<FileInfo> list = new ArrayList();
                Set set = map.keySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    BusinessInfo businessInfo = map.get(iterator.next());
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setCarCode(businessInfo.getCarCode());
                    fileInfo.setCarNumber(businessInfo.getCarNumber());
                    fileInfo.setCkStatus(ConstantUtil.CK_STATUS_1);
                    fileInfo.setFileCode(businessInfo.getFileCode());
                    fileInfo.setHphm(businessInfo.getPlateCode());
                    fileInfo.setHpzl(businessInfo.getPlateType());
                    fileInfo.setStatus(ConstantUtil.ZC_FILE);

                    list.add(fileInfo);
                }
                /*
                *  移除数据库重复档案 并将新档案添加到档案表
                * */
                List<FileInfo> fileInfos = null;
                if (list.size() > 0) {
                    fileInfos = fileInfoMapper.findFileInfos(list);
                    if (fileInfos.size() > 0) {
                        for (int w = 0; w < list.size(); w++) {
                            for (int f = 0; f < fileInfos.size(); f++) {
                                if (list.get(w).getFileCode().equals(fileInfos.get(f).getFileCode())) {
                                    list.remove(w);
                                    w--;
                                    break;
                                }
                            }
                        }
                    }
                    if (list.size() > 0) {
                        fileInfoMapper.addFileInfo(list);
                    }
                }
                /*
                * 添加档案id
                * */
                for (BusinessInfo b : businessInfosAdd) {
                    if (list != null && list.size() > 0) {
                        for (int s = 0; s < list.size(); s++) {
                            if (b.getFileCode().equals(list.get(s).getFileCode())) {
                                b.setFileId(list.get(s).getFileId());
                            }
                        }
                    }
                    if (fileInfos != null && fileInfos.size() > 0) {
                        for (int a = 0; a < fileInfos.size(); a++) {
                            if (b.getFileCode().equals(fileInfos.get(a).getFileCode())) {
                                b.setFileId(fileInfos.get(a).getFileId());
                            }
                        }
                    }
                }
                businessInfoMapper.addBusinessInfo(businessInfosAdd);
                logger.info("addAToBRegister  -- 流水添加完成");
            }
            for (BusinessInfo b : businessInfosAdd) {
                integers.add(b.getBusinessId());
            }
            /*
            *   添加批次号
            * */
            String code = new SimpleDateFormat("yyyyMMdd").format(new Date());
            Integer count = 0;
            String codeMax = deliveryInfoMapper.getMaxDeliveryId();  //获取最大批次
            String dateStr = "";
            if (codeMax != null && !codeMax.equals("")) {
                dateStr = codeMax.substring(0, 8);         //截取年月日
            }

            if (!code.equals(dateStr) || dateStr.equals("")) {      //判断是否是当天批次
                code += "000001";
            } else {
                codeMax = codeMax.substring(codeMax.length() - 6);
                count = Integer.parseInt(codeMax);
                code += String.format("%06d", count + 1);
            }
            DeliveryInfo deliveryInfo = new DeliveryInfo();
            deliveryInfo.setCode(code);
            deliveryInfo.setJjlx(ConstantUtil.A_TO_B_DJ);
            deliveryInfo.setRegisterPerson(userId);
            deliveryInfo.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            deliveryInfoMapper.addDeliveryInfo(deliveryInfo);
            logger.info("addAToBRegister  -- 添加批次号");
            deliveryInfoMapper.addDeliveryInfoRel(deliveryInfo.getDeliveryId(), integers);
            logger.info("addAToBRegister  -- 添加批次号详情");
            Signing signing = new Signing();
            signing.setCode(code);
            signing.setJjlx(ConstantUtil.A_TO_B_DJ);
            signing.setBox(box);
            signing.setUpdateUser(userId);
            signingMapper.addSigning(signing);
            logger.info("addAToBRegister  -- 添加箱子");
            logger.info(deliveryInfo.toString() + " -- 登记数量 add:" + businessInfosAdd.size() + "  -- update : " + businessInfosUpd.size());
            return deliveryInfo;
        }
        logger.info("addAToBRegister -end ");
        return new DeliveryInfo();
    }

    @Override
    public List<DeliveryInfo> findDeliveryByCodeAndBoxAndDate(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 3, name = "start", requiredness = ThriftField.Requiredness.NONE) String start, @ThriftField(value = 4, name = "end", requiredness = ThriftField.Requiredness.NONE) String end, @ThriftField(value = 5, name = "indexPage", requiredness = ThriftField.Requiredness.NONE) int indexPage, @ThriftField(value = 6, name = "pageCount", requiredness = ThriftField.Requiredness.NONE) int pageCount) throws TException {
        logger.info("addAToBRegister  -begin   -   code : " + code + "  box :" + box + "  start : " + start + "  end : " + end);
        List<UserInfo> userInfos = userInfoMapper.getAllUser();
        Map<Integer, String> userMap = new HashMap<>();
        for (UserInfo u : userInfos) {
            userMap.put(u.getUserId(), u.getName());
        }
        if(StringUtils.isBlank(code)&&StringUtils.isNotBlank(box)){
            code = signingMapper.findPcCodeByBox(box);
            logger.info("addAToBRegister  通过箱号查询批次号- code :"+code);
            if(StringUtils.isBlank(code)){
                code = "-1"; //表示没有查到数据
            }
        }
        List<DeliveryInfo> deliveryInfos = new ArrayList<>();
        if(!code.equals("-1")){
            deliveryInfos  = deliveryInfoMapper.findDeliveryByCodeAndDate
                    (code,start == null || start.equals("") ? "" : start, end == null || end.equals("") ? "" : end, new RowBounds(indexPage, pageCount));
            List<Signing> signings = signingMapper.findSigningByPcCodes(deliveryInfos);
            for (DeliveryInfo d:deliveryInfos){
                d.setRegisterPersonName(userMap.get(d.getRegisterPerson()));
                for(Signing s:signings){
                    if(s.getCode().equals(d.getCode())){
                        d.setReceiverName(s.getReceiver() != 0?userMap.get(s.getReceiver()):"");
                        d.setBox(s.getBox());
                        d.setJjlx(s.getStatus());
                        d.setSigningId(s.getSigningId());
                    }
                }
            }
        }
        logger.info("findDeliveryByCodeAndBoxAndDate -end ");
        return deliveryInfos;
    }

    @Override
    public long findDeliveryByCodeAndBoxAndDateCount(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 3, name = "start", requiredness = ThriftField.Requiredness.NONE) String start, @ThriftField(value = 4, name = "end", requiredness = ThriftField.Requiredness.NONE) String end, @ThriftField(value = 5, name = "indexPage", requiredness = ThriftField.Requiredness.NONE) int indexPage, @ThriftField(value = 6, name = "pageCount", requiredness = ThriftField.Requiredness.NONE) int pageCount) throws TException {
        logger.info("findDeliveryByCodeAndBoxAndDateCount - begin -   code : " + code + "  box :" + box + "  start : " + start + "  end : " + end);
        if(StringUtils.isBlank(code)&&StringUtils.isNotBlank(box)){
            code = signingMapper.findPcCodeByBox(box);
            logger.info("addAToBRegister  通过箱号查询批次号- code :"+code);
            if(StringUtils.isBlank(code)){
                code = "-1"; //表示没有查到数据
            }
        }
        long count = 0;
        if(!code.equals("-1")){
            count = deliveryInfoMapper.findDeliveryByCodeAndDateCount(code, start == null || start.equals("") ? "" : start, end == null || end.equals("") ? "" : end);
        }
        logger.info("findDeliveryByCodeAndBoxAndDateCount -end ");
        return count;
    }


    @Override
    public List<DeliveryInfo> getJjlxByCodes(@ThriftField(value = 1, name = "deliveryinfos", requiredness = ThriftField.Requiredness.NONE) List<DeliveryInfo> deliveryinfos) throws TException {

        List<DeliveryInfo> deliveryInfos = deliveryInfoMapper.findRegisterNumberByCode(deliveryinfos);
        List<Signing> signings = signingMapper.findSignNumberByCode(deliveryinfos);
        for (DeliveryInfo d : deliveryInfos) {
            for (Signing s : signings) {
                if (d.getCode().equals(s.getCode())) {
                    if (d.getCount() == s.getCount()) {
                        d.setJjlx(1);
                        break;
                    } else if (d.getCount() > s.getCount() && s.getCount() != 0) {
                        d.setJjlx(3);
                        break;
                    }
                }
            }
        }

        return deliveryInfos;
    }

    @Override
    public List<BusinessInfo> ajaxLookSignDetailByCode(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "bs", requiredness = ThriftField.Requiredness.NONE) String bs) throws TException {
        List<BusinessInfo> businessInfos = null;
        logger.info(" ajaxLookSignDetailByCode -  begin  code ; "+code+" bs"+bs);
        if (bs.equals("signBussiness")) {
            businessInfos = businessInfoMapper.ajaxLookSignDetailByCode(code, ConstantUtil.A_TO_B_DJ, ConstantUtil.B_TO_A_DJ);
        } else if (bs.equals("lookDetail")) {
            businessInfos = businessInfoMapper.ajaxLookSuccessSignDetailByCode(code, ConstantUtil.A_TO_B_DJ);
        }
        for (BusinessInfo businessInfo : businessInfos) {
            businessInfo.setPlateType(businessInfo.getPlateType()==null?"":ConstantsEnum.Hpzl.getHpzlByType(businessInfo.getPlateType()).name());
        }
        logger.info(" ajaxLookSignDetailByCode -  businessInfos.size() :"+ businessInfos.size());
        logger.info(" ajaxLookSignDetailByCode -end ");
        return businessInfos;
    }


    @Override
    public BusinessInfo findBoxByDeliveryIdAndLsh(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "deliveryId", requiredness = ThriftField.Requiredness.NONE) String deliveryId) throws TException {
        if (code.length() == 11) {
            code = code.substring(1, 11);
            code = "1A" + code + 0;
        }
        String businessId = businessInfoMapper.findBusinessIdByCode(code);
        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setBusinessId(businessId==null?0:Integer.parseInt(businessId));
        businessInfo.setCode(code);
        return businessInfo;
    }

    @Override
    public int signBoxInAToB(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 3, name = "deliveryId", requiredness = ThriftField.Requiredness.NONE) String deliveryId) throws TException {
        logger.info("signBoxInAToB -begin  businessInfo :" + businessInfo + "  deliveryId :" + deliveryId + "  userId :" + userId);
        signingMapper.addSigningRef(businessInfo, userId);
        List<Integer> i = signingMapper.getBoxSigningState(businessInfo.getBox(), deliveryId);//交接情况
        businessInfoMapper.updateZt(businessInfo.getBusinessId(), ConstantUtil.A_TO_B_QS);
        if (i.get(0).equals(i.get(1))) {
            businessInfoMapper.updateWz(businessInfo.getBox(), ConstantUtil.B);
            signingMapper.updateJjzt(businessInfo.getBox(), ConstantUtil.A_TO_B_QS, userId, ConstantsEnum.SigningStatus.已签收.getValue());
        } else if (i.get(0).equals(1)) {
            Signing signing = new Signing();
            signing.setSigningId(Integer.parseInt(businessInfo.getBox()));
            signing.setStatus(ConstantsEnum.SigningStatus.签收中.getValue());
            signingMapper.updateSigingStatus(signing);
        }
        logger.info("signBoxInAToB -end  ");
        return i.get(0).equals(i.get(1)) ? 1 : 0;
    }

    @Override
    public List<DeliveryInfo> getBusinessInfoListByDateOrCodeRefUser(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "user", requiredness = ThriftField.Requiredness.NONE) UserInfo user, @ThriftField(value = 3, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 4, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 5, name = "indexPage", requiredness = ThriftField.Requiredness.NONE) int indexPage, @ThriftField(value = 6, name = "pageCount", requiredness = ThriftField.Requiredness.NONE) int pageCount, @ThriftField(value = 7, name = "users", requiredness = ThriftField.Requiredness.NONE) List<UserInfo> users) throws TException {
        Map<Integer, UserInfo> map = new HashMap<>();
        for (UserInfo ui : users) {
            map.put(ui.getUserId(), ui);
        }
        boolean isAdmin = false;
        if (user.getIsSuperAdmin() != 1) {  //是否为超级管理员
            //判断是否为管理员
            List<Integer> roleId = roleMapper.getRoleIdsByUserId(user.getUserId());//根据用户id获取该用户角色id
            for (Integer i : roleId) {
                if (i == 4) {   //角色id等于4 表示管理员
                    isAdmin = true;
                }
            }
        } else {
            isAdmin = true;
        }
        //获取登记信息    :   根据用户权限获取登记信息，非管理员权限的用户，只能查看用户本身出库登记的登记信息
        List<DeliveryInfo> deliveryInfos = deliveryInfoMapper.getDeliveryListByUserPurview
                (code, user.getUserId(), startTime == null || startTime.equals("") ? "" : startTime, endTime == null || endTime.equals("") ? "" : endTime, isAdmin, new RowBounds(indexPage, pageCount));
        if (deliveryInfos.size() > 0) {
            List<Signing> signings = signingMapper.findSigningByPcCodes(deliveryInfos);
            for (DeliveryInfo di : deliveryInfos) {
                UserInfo ui1 = map.get(di.getRegisterPerson());
                if (ui1 != null) {
                    di.setRegisterPersonName(ui1.getName());
                }
                for (Signing s : signings) {
                    if (di.getCode().equals(s.getCode())) {
                        di.setJjlx(s.getStatus());
                        di.setRealJjlx(s.getJjlx());
                        di.setBox(s.getBox());
                        UserInfo ui2 = map.get(s.getReceiver());
                        if (ui2 != null) {
                            di.setReceiverName(ui2.getName());
                        }
                    }
                }
            }
        }
        return deliveryInfos;
    }

    @Override
    public int getBusinessInfoListByDateOrCodeRefUserCount(@ThriftField(value = 1, name = "code", requiredness = ThriftField.Requiredness.NONE) String code, @ThriftField(value = 2, name = "user", requiredness = ThriftField.Requiredness.NONE) UserInfo user, @ThriftField(value = 3, name = "startTime", requiredness = ThriftField.Requiredness.NONE) String startTime, @ThriftField(value = 4, name = "endTime", requiredness = ThriftField.Requiredness.NONE) String endTime, @ThriftField(value = 7, name = "users", requiredness = ThriftField.Requiredness.NONE) List<UserInfo> users) throws TException {
        Map<Integer, UserInfo> map = new HashMap<>();
        for (UserInfo ui : users) {
            map.put(ui.getUserId(), ui);
        }
        boolean isAdmin = false;
        if (user.getIsSuperAdmin() != 1) {  //是否为超级管理员
            //判断是否为管理员
            List<Integer> roleId = roleMapper.getRoleIdsByUserId(user.getUserId());//根据用户id获取该用户角色id
            for (Integer i : roleId) {
                if (i == 4) {   //角色id等于4 表示管理员
                    isAdmin = true;
                }
            }
        } else {
            isAdmin = true;
        }
        return deliveryInfoMapper.getDeliveryListByUserPurviewCount
                (code, user.getUserId(), startTime == null || startTime.equals("") ? "" : startTime, endTime == null || endTime.equals("") ? "" : endTime, isAdmin);
    }


    @Override
    public int updateBox(@ThriftField(value = 1, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 2, name = "beforeBox", requiredness = ThriftField.Requiredness.NONE) String beforeBox) throws TException {
        logger.info("updateBox -begin  box :" + box + "  beforeBox :" + beforeBox);
        String i = signingMapper.findBox(box);
        if (i == null) {
            signingMapper.updateBox(box, beforeBox);
            businessInfoMapper.updateBox(box, beforeBox);
            return 1;
        }
        logger.info("updateBox -end  ");
        return 0;
    }

    @Override
    public List<BusinessInfo> getBusinessInfoListByBox(@ThriftField(value = 1, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 2, name = "deliveryId", requiredness = ThriftField.Requiredness.NONE) String deliveryId, @ThriftField(value = 3, name = "deliveryCode", requiredness = ThriftField.Requiredness.NONE) String deliveryCode) throws TException {
        logger.info("getBusinessInfoListByBox -begin  box :" + box + "  deliveryId :" + deliveryId + "  deliveryCode :" + deliveryCode);
        List<BusinessInfo> businessInfos = null;
        businessInfos = businessInfoMapper.getBusinessInfoListByDeliveryId(deliveryId);
        Signing signing = signingMapper.findSigningByPcCode(deliveryCode);
        if (signing != null) {
            List<SigningRel> signingRels = null;
            signingRels = signingMapper.findSigningRelBySigningId(signing.getSigningId());
            for (BusinessInfo businessInfo : businessInfos) {
                businessInfo.setPlateType(ConstantsEnum.Hpzl.getHpzlByType(businessInfo.getPlateType()).name());
                if (signingRels != null && signingRels.size() > 0) {
                    for (SigningRel signingRel : signingRels) {
                        if (businessInfo.getBusinessId() == signingRel.getBusinessId()) {
                            businessInfo.setLszt(2);     //设置为  2  使前端不能对此业务修改
                            break;
                        }
                    }
                }
            }
        }
        logger.info("getBusinessInfoListByBox -end  ");
        return businessInfos;
    }

    @Override
    public void delDeliveryRel(@ThriftField(value = 1, name = "ids", requiredness = ThriftField.Requiredness.NONE) String ids, @ThriftField(value = 2, name = "box", requiredness = ThriftField.Requiredness.NONE) String box, @ThriftField(value = 3, name = "deliveryId", requiredness = ThriftField.Requiredness.NONE) String deliveryId, @ThriftField(value = 4, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 5, name = "deliveryCode", requiredness = ThriftField.Requiredness.NONE) String deliveryCode) throws TException {
        logger.info("delDeliveryRel -begin  ids :" + ids + "  box :" + box + "  userId :" + userId + "  deliveryId :" + deliveryId + "  deliveryCode :" + deliveryCode);
        String[] str = ids.split(",");
        List<BusinessInfo> businessInfos = businessInfoMapper.getBusinessInfoListById(str);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        //判断这些流水是在A库还是在B库
        for (BusinessInfo bi : businessInfos) {
            if (bi.getLswz() == ConstantUtil.A) {
                a.add(bi.getBusinessId());
            } else if (bi.getLswz() == ConstantUtil.B) {
                b.add(bi.getBusinessId());
            }
        }
        int wz = 0;
        int zt = 0;
        if (a.size() > 0) { //如果在A库，将流水状态返回到B转A签收的状态
            businessInfoMapper.updateBusinessInfosZt(a, ConstantUtil.B_TO_A_QS);
            wz = ConstantUtil.B;
            zt = ConstantUtil.A_TO_B_QS;
        } else if (b.size() > 0) {//如果在B库，将流水状态返回到A转B签收的状态
            businessInfoMapper.updateBusinessInfosZt(b, ConstantUtil.A_TO_B_QS);
            wz = ConstantUtil.A;
            zt = ConstantUtil.B_TO_A_QS;
        }
        deliveryInfoMapper.delDeliveryRel(str, deliveryId); //删除该批次的登记明细
        //如果该批次登记的流水明细都删了，那么把该批次的登记记录也删掉，签收记录也删掉
        if (deliveryInfoMapper.getDjCountByPcCode(deliveryCode) == 0) {
            deliveryInfoMapper.delDeliveryByPcCode(deliveryCode);
            //Signing signing=signingMapper.findSigningByPcCode(deliveryCode);
            //if(signing!=null){
            signingMapper.delSigningByPcCode(deliveryCode);
            //}
        } else {
            //        String signingId = signingMapper.findBox(box);
            String signingId = signingMapper.findSigningIdByDeliveryCode(deliveryCode);
//        int i = signingMapper.getBoxJjqk(signingId + "", deliveryId);//交接情况
            List<Integer> i = signingMapper.getBoxSigningState(signingId + "", deliveryId);
            if (i.get(0).equals(i.get(1))) {
                businessInfoMapper.updateWz(signingId + "", wz);
                signingMapper.updateJjzt(signingId + "", zt, userId, ConstantsEnum.SigningStatus.已签收.getValue());
//            deliveryInfoMapper.updateJjzt(deliveryId, ConstantUtil.A_TO_B_QS);
            }
        }
        logger.info("delDeliveryRel -end  ");
    }


    @Override
    public List<BusinessInfo> getBusinessInfoListForImage(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        List<String> codeList = null;
        List<ImageInfo> imageInfoList = null;
        List imageCodeList = null;
        List<BusinessInfo> businessInfoListAll = businessInfoMapper.getBusinessInfoListForImage(businessInfo);
        //获取codeList
        if(businessInfoListAll!=null&&businessInfoListAll.size()>0) {
            codeList = Collections3.extractToList(businessInfoListAll, "code");
        }
        //根据codeList查询影像记录
        if(codeList!=null&&codeList.size()>0) {
            imageInfoList = imageInfoMapper.getImageInfoListByCode(codeList, new RowBounds(startIndex, pageSize));
        }
        //获取影像流水
        if (imageInfoList!=null&&imageInfoList.size()>0) {
            imageCodeList = Collections3.extractToList(imageInfoList, "code");
        }
        //根据影像流水查询流水记录

        List<BusinessInfo> businessInfoList= businessInfoMapper.getBusinessInfoListByCode(imageCodeList);

        Map<String, String> businessTypeMap=cacheService.getBusinessType();
        Map<String, String> platTypeMap=cacheService.getPlatType();
        if (businessInfoList!=null&&businessInfoList.size()>0){
            for(BusinessInfo businessInfo1:businessInfoList){
                businessInfo1.setBusinessType(businessTypeMap.get(businessInfo1.getBusinessType()));
                businessInfo1.setPlateType(platTypeMap.get(businessInfo1.getPlateType()));
            }

        }
        Map<String,ImageInfo> mapImageInfo = Collections3.extractToKeyObjectMap(imageInfoList,"code");
        for(BusinessInfo businessInfo1:businessInfoList) {
            businessInfo1.setImageUser(mapImageInfo.get(businessInfo1.getCode()).getImageUser());
            businessInfo1.setImageDate(mapImageInfo.get(businessInfo1.getCode()).getImageDate());

        }
        if (businessInfoList != null && businessInfoList.size() > 0) {
            //查用户
            List<UserInfo> userInfoList = userInfoMapper.getAllUser();
            Map<Integer, String> userMap = new HashMap<>();
            for (UserInfo u : userInfoList) {
                userMap.put(u.getUserId(), u.getName());
            }
            for (BusinessInfo b : businessInfoList) {
                b.setImageUserName(userMap.get(b.getImageUser()) == null ? "未知" : userMap.get(b.getImageUser()));
            }
            return businessInfoList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public long getBusinessInfoListForImageCount(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {

        long count= 0;
        List<String> codeList = null;
        List<ImageInfo> imageInfoList = null;
        List imageCodeList = null;
        List<BusinessInfo> businessInfoListAll = businessInfoMapper.getBusinessInfoListForImage(businessInfo);
        //获取codeList
        if(businessInfoListAll!=null&&businessInfoListAll.size()>0) {
            codeList = Collections3.extractToList(businessInfoListAll, "code");
        }
        //根据codeList查询影像记录条数
        if(codeList!=null&&codeList.size()>0) {
            count = imageInfoMapper.getImageInfoListCountByCode(codeList);
        }
        return count;

        //        List<BusinessInfo> businessInfoListAll = businessInfoMapper.getBusinessInfoListForImage(businessInfo, new RowBounds(startIndex, pageSize));
//        List<BusinessInfo> businessInfoList = new ArrayList<>();
//        long businessInfoCount=0;
//        Map<String, String> businessTypeMap=cacheService.getBusinessType();
//        Map<String, String> platTypeMap=cacheService.getPlatType();
//        for(BusinessInfo businessInfo1:businessInfoListAll){
//            businessInfo1.setBusinessType(businessTypeMap.get(businessInfo1.getBusinessType()));
//            businessInfo1.setPlateType(platTypeMap.get(businessInfo1.getPlateType()));
//        }
//
//        List<String> codeList = Collections3.extractToList(businessInfoListAll,"code");
//        List<ImageInfo> imageInfoList =businessInfoMapper.getImageInfoListByCode(codeList);
//        Map<String,ImageInfo> mapImageInfo = Collections3.extractToKeyObjectMap(imageInfoList,"code");
//        for(BusinessInfo businessInfo1:businessInfoListAll) {
//            if(mapImageInfo.get(businessInfo1.getCode())==null) {
//                continue;
//            }else {
//                businessInfo1.setImageUser(mapImageInfo.get(businessInfo1.getCode()).getImageUser());
//                businessInfo1.setImageDate(mapImageInfo.get(businessInfo1.getCode()).getImageDate());
//                businessInfoList.add(businessInfo1);
//            }
//
//        }
//
//        //long businessInfoCount=businessInfoMapper.getBusinessInfoListForImageCount(businessInfo1, new RowBounds(startIndex, pageSize));
//        if(businessInfoList.size()==0){
//            businessInfoCount=0;
//        }else {
//            businessInfoCount=businessInfoList.size();
//        }
//        return businessInfoCount;
    }

    @Override
    public void updateQsZl(@ThriftField(value = 1, name = "businessId", requiredness = ThriftField.Requiredness.NONE) String businessId, @ThriftField(value = 2, name = "businessDetailIds", requiredness = ThriftField.Requiredness.NONE) String businessDetailIds, @ThriftField(value = 3, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 4, name = "fileId", requiredness = ThriftField.Requiredness.NONE) String fileId) throws TException {
        logger.info("updateQsZl -begin  businessId :" + businessId + "  fileId :" + fileId + "  userId :" + userId + "  businessDetailIds :" + businessDetailIds);
        businessTypeMapper.deleteBusinessQsZl(businessId);
        if (businessDetailIds != null && !businessDetailIds.equals("")) {
            String[] businessDetailIdList = businessDetailIds.split(",");
            businessTypeMapper.updateQsZl(businessId, businessDetailIdList, userId);
            businessInfoMapper.updateQsStatus(ConstantUtil.ZL_QS_BUSINESS, businessId);
            fileInfoMapper.updateStatus(ConstantUtil.WT_FILE, fileId);
        } else {
            businessInfoMapper.updateQsStatus(ConstantUtil.ZC_BUSINESS, businessId);
            int count = businessInfoMapper.countQsStatusByFileId(fileId, ConstantUtil.QS_BUSINESS, ConstantUtil.ZL_QS_BUSINESS);
            if (count == 0) {
                fileInfoMapper.updateStatus(ConstantUtil.ZC_FILE, fileId);
            }
        }
        logger.info("updateQsZl -end  ");
    }

    @Override
    public void addBusinessQszl(String lsh, String userId) throws TException {
        List<BusinessImageDTO> businessImageDTOList = findBusinessIdAndImageIdAndBusinessTypeIdByLsh(lsh);
        if (businessImageDTOList != null && businessImageDTOList.size() > 0) {
            BusinessImageDTO businessImageDTO = businessImageDTOList.get(0);
            if (!businessImageDTO.getBusinessType().equalsIgnoreCase(ConstantsEnum.businessType.其他.getValue())) {
                List<BusinessTypeDetail> businessTypeDetailList = businessTypeMapper.findBusinessTypeDetailByBstIdAndIsSm(businessImageDTO.getBusinessTypeId(), ConstantsEnum.isSmStatus.扫描.getValue());
                List<ImagePhotoRel> imagePhotoRelList = imagePhotoRelMapper.getImagePhotoRelList(businessImageDTO.getImageId());
                List<BusinessQszlRel> qszlRels = new ArrayList<>();
                for (BusinessTypeDetail b : businessTypeDetailList) {
                    int i = 0;
                    for (ImagePhotoRel rel : imagePhotoRelList) {
                        if (rel.getBtdId() == b.getBusinessDetailId()) {
                            i++;
                            break;
                        }
                    }
                    if (i == 0) {
                        BusinessQszlRel bqr = new BusinessQszlRel();
                        bqr.setBusinessId(businessImageDTO.getBusinessId());
                        bqr.setCreateTime(sf.format(new Date()));
                        bqr.setBusinessDetailId(b.getBusinessDetailId());
                        bqr.setCreateUser(Integer.parseInt(userId));
                        qszlRels.add(bqr);
                    }
                }
                businessInfoMapper.deleteBusinessQszlRelByBusinessId(businessImageDTO.getBusinessId());
                if (qszlRels.size() > 0) {
                    businessInfoMapper.addBusinessQszlRels(qszlRels);
                    businessInfoMapper.updateQsStatus(ConstantsEnum.QsStatus.资料缺失.getValue(), String.valueOf(businessImageDTO.getBusinessId()));//更新流水的缺失状态为资料缺失
                    fileInfoMapper.updateStatus(ConstantsEnum.FileStatus.问题档案.getValue(), String.valueOf(businessImageDTO.getFileId()));//更新档案状态为问题档案
                } else {
                    businessInfoMapper.updateQsStatus(ConstantsEnum.QsStatus.正常.getValue(), String.valueOf(businessImageDTO.getBusinessId()));//更新流水的缺失状态为正常
                    fileInfoMapper.updateStatus(ConstantsEnum.FileStatus.正常档案.getValue(), String.valueOf(businessImageDTO.getFileId()));//更新档案状态为正常档案
                }
            }
        }
    }

    @Override
    public List<BusinessImageDTO> findBusinessIdAndImageIdAndBusinessTypeIdByLsh(String lsh) throws TException {
        List<BusinessImageDTO> businessImageDTOS = businessInfoMapper.findBusinessIdAndImageIdAndBusinessTypeIdByLsh(lsh);
        if (businessImageDTOS != null && businessImageDTOS.size() > 0) {
            return businessImageDTOS;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int addFileInfo(BusinessInfo businessInfo) throws TException {
        //FileInfo fileInfo=businessInfoMapper.findFileInfoByFileCode(businessInfo.getFileCode());
//        if(fileInfo==null){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setCarCode(businessInfo.getCarCode());
        fileInfo.setCarNumber(businessInfo.getCarNumber());
        fileInfo.setCkStatus(ConstantUtil.CK_STATUS_1);
        fileInfo.setFileCode(businessInfo.getFileCode());
        fileInfo.setHphm(businessInfo.getPlateCode());
        fileInfo.setHpzl(businessInfo.getPlateType());
        fileInfo.setStatus(ConstantUtil.ZC_FILE);
        return fileInfoMapper.addFile(fileInfo);
//        }
    }

    @Override
    public List<Jjtj> getJxrwqsList(String startTime, String endTime) throws TException {
        List<Jjtj> jjtjList = businessInfoMapper.getJxrwSignList(startTime, endTime);
        List<Jjtj> jjtjListNew = new ArrayList<>();
        if (jjtjList == null) {
            return new ArrayList<>();
        } else {
            List<Department> departments = departmentMapper.getDepartmentList();
            List<Department> jxDepartment = new ArrayList<>();
            for (Department d : departments) {
                if (d.getCode().equals("lsdajjz") || d.getCode().equals("ysmdajjz")) {
                    jxDepartment.add(d);
                }
            }
            Integer lsdajjzId = 0;
            Integer ysmdajjzId = 0;
            for (Department department : jxDepartment) {
                if (department.getCode().equals("lsdajjz")) {
                    lsdajjzId = department.getDepartmentId();
                } else if (department.getCode().equals("ysmlsdajjz")) {
                    ysmdajjzId = department.getDepartmentId();
                }
            }
            for (Jjtj j : jjtjList) {
                if (j.getDepartmentId() == lsdajjzId) {
                    j.setJjlxStr("A转B库签收");
                    j.setDepartmentName("历史档案交接组");
                    jjtjListNew.add(j);
                } else if (j.getDepartmentId() == ysmdajjzId) {
                    j.setJjlxStr("A转B库签收");
                    j.setDepartmentName("已扫描档案交接组");
                    jjtjListNew.add(j);
                }
            }
            return jjtjListNew;
        }
    }

    @Override
    public List<BusinessInfo> findFileInfoByBox(@ThriftField(value = 1, name = "box", requiredness = ThriftField.Requiredness.NONE) String box) throws TException {
        return businessInfoMapper.findFileInfoByBox(box);
    }

    @Override
    public List<String> findNotSignCodeByBox(@ThriftField(value = 1, name = "box", requiredness = ThriftField.Requiredness.NONE) String box) throws TException {
        logger.info("findNotSignCodeByBox - begin  -- box : "+box);
        List<String> codes = businessInfoMapper.findNotSignCodeByBox(box);
        logger.info("findNotSignCodeByBox - end  -- codes : "+codes.size());
        return codes;
    }



    @Override
    public BusinessInfo findNativeBusinessInfoByLsh(@ThriftField(value = 1, name = "lsh", requiredness = ThriftField.Requiredness.NONE) String lsh) throws TException {
        BusinessInfo businessInfo = businessInfoMapper.findNativeBusinessInfoByLsh(lsh);
        Map<String,String> platTypeMap= cacheService.getPlatType();
        businessInfo.setPlateType(platTypeMap.get(businessInfo.getPlateType()));
        Map<String,String> businessTypeMap= cacheService.getBusinessType();
        businessInfo.setBusinessType(businessTypeMap.get(businessInfo.getBusinessType()));
        if (businessInfo != null) {
            return businessInfo;
        } else {
            return new BusinessInfo();
        }
    }


    @Override
    @Transactional
    public String BToARegister(@ThriftField(value = 1, name = "businessInfos", requiredness = ThriftField.Requiredness.NONE) List<BusinessInfo> businessInfos, @ThriftField(value = 2, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId) throws TException {
        logger.info("BToARegister -begin  businessInfos.size() :" + businessInfos.size() + "  userId :" + userId);

        List<Integer> idList = new ArrayList<>();
        for (BusinessInfo b : businessInfos) {
            idList.add(b.getBusinessId());
        }
        businessInfoMapper.deliveryBToA(businessInfos, ConstantUtil.B, ConstantUtil.B_TO_A_DJ);
        String code = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Integer count = 0;
        String codeMax = deliveryInfoMapper.getMaxDeliveryId();
        String dateStr = codeMax.substring(0, 8);
        if (!code.equals(dateStr)) {
            code += "000001";
        } else {
            codeMax = codeMax.substring(codeMax.length() - 6);
            count = Integer.parseInt(codeMax);
            code += String.format("%06d", count + 1);
        }
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCode(code);
        deliveryInfo.setJjlx(ConstantUtil.B_TO_A_DJ);
        deliveryInfo.setRegisterPerson(userId);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        deliveryInfo.setCreateDate(sf.format(date));
        deliveryInfo.setRegisterDate(sf.format(date));
        deliveryInfoMapper.addDeliveryInfo(deliveryInfo);
        deliveryInfoMapper.addDeliveryInfoRel(deliveryInfo.getDeliveryId(), idList);
        logger.info("BToARegister -end  ");
        return code;//返回批次号
    }

    @Override
    public List<BusinessInfo> findNativeBusinessInfoByDeliveryPcCode(@ThriftField(value = 1, name = "pcCode", requiredness = ThriftField.Requiredness.NONE) String pcCode) throws TException {
        List<DeliveryInfo> deliveryList = deliveryInfoMapper.findDeliveryListByPcCode(pcCode);
        List<Integer> businessId = Collections3.extractToList(deliveryList, "businessId");
        String[] strs = new String[businessId.size()];
        for (int i = 0; i <businessId.size(); i++) {
            strs[i] = String.valueOf(businessId.get(i));
        }

        List<BusinessInfo> businessInfoList = businessInfoMapper.getBusinessInfoListById(strs);
        Map<String, String> platTypeMap=cacheService.getPlatType();
        for(BusinessInfo businessInfo1:businessInfoList){
            //businessInfo1.setBusinessType(businessTypeMap.get(businessInfo1.getBusinessType()));
            businessInfo1.setPlateType(platTypeMap.get(businessInfo1.getPlateType()));
        }
        if (businessInfoList != null && businessInfoList.size() > 0) {
            return businessInfoList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int saveBusinessInfo(@ThriftField(value = 1, name = "businessInfo", requiredness = ThriftField.Requiredness.NONE) BusinessInfo businessInfo) throws TException {
        return businessInfoMapper.saveBusinessInfo(businessInfo);
    }


}
