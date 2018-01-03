package com.igool.rpc.db.service.impl;

import com.igool.rpc.db.mapper.ImagePhotoRelMapper;
import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.mysql.mapper.BusinessTypeMapper;
import com.igool.rpc.db.mysql.mapper.ImageInfoMapper;
import com.igool.rpc.db.mysql.mapper.VehicleMapper;
import com.igool.rpc.db.oracle.mapper.SynVehicleMapper;
import com.igool.rpc.db.oracle.mapper.SynchroLibraryMapper;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.rpc.db.service.thrift.VehicleService;
import com.igool.util.ConstantsEnum;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/11/15.
 */
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    SynVehicleMapper synVehicleMapper;
    @Autowired
    VehicleMapper vehicleMapper;
    @Autowired
    SynchroLibraryMapper synchroLibraryMapper;
    @Autowired
    ImageInfoMapper imageInfoMapper;
    @Autowired
    ImagePhotoRelMapper imagePhotoRelMapper;
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public void addVehicleOperator(VehicleOperator vehicleoperator) throws TException {
        vehicleMapper.addVehicleOperator(vehicleoperator);
    }

    @Override
    public List<FindRegister> getFindRegisterByTypeAndStr(String str) throws TException {
        List<FindRegister> findRegisters=vehicleMapper.getFindRegisterByTypeAndStr(str);
        if(findRegisters!=null&&findRegisters.size()>0){
            return findRegisters;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<VehicleDetailDTO> findAllVehicleBySfz(String sfzs, String hphms, String hpzls) throws TException {
        StringBuffer newSfz=new StringBuffer();
        StringBuffer newHphm=new StringBuffer();
        StringBuffer newHpzl=new StringBuffer();
        if(sfzs.indexOf(",")<0){
            newSfz.append("('").append(sfzs).append("')");
        }else{
            String[] arr1=sfzs.split(",");
            newSfz.append("(");
            for(int i=0;i<arr1.length;i++){
                newSfz.append("'").append(arr1[i]).append("'");
                if(i!=arr1.length-1){
                    newSfz.append(",");
                }
            }
            newSfz.append(")");
        }
        if(!hphms.contains(",")){
            newHphm.append("('").append(hphms).append("')");
        }else{
            String[] arr2=hphms.split(",");
            newHphm.append("(");
            for(int i=0;i<arr2.length;i++){
                newHphm.append("'").append(arr2[i]).append("'");
                if(i!=arr2.length-1){
                    newHphm.append(",");
                }
            }
            newHphm.append(")");
        }

        if(!hpzls.contains(",")){
            newHpzl.append("('").append(hpzls).append("')");
        }else{
            String[] arr2=hpzls.split(",");
            newHpzl.append("(");
            for(int i=0;i<arr2.length;i++){
                newHpzl.append("'").append(arr2[i]).append("'");
                if(i!=arr2.length-1){
                    newHpzl.append(",");
                }
            }
            newHpzl.append(")");
        }
        //下面改成根据号牌号码和号牌种类查，得到的结果再根据sfz筛选，不然查的太慢，会影响数据库
        List<VehicleDetail> vehicleDetailsTemp=synVehicleMapper.findVehicleDetailsBySfz(newSfz.toString(),newHphm.toString(),newHpzl.toString());
        List<VehicleDetail> vehicleDetails=new ArrayList<>();
        if(vehicleDetailsTemp==null||vehicleDetailsTemp.size()==0){
            return new ArrayList<>();
        }else{
            for(VehicleDetail v:vehicleDetailsTemp){//根据身份证筛选数据
                if(v.getSfz()!=null&&!v.getSfz().equals("")&&sfzs.contains(v.getSfz())){
                    vehicleDetails.add(v);
                }
            }
        }
        List<VehicleFlow> vehFlows = new ArrayList<VehicleFlow>();
        if(vehicleDetails!=null&&vehicleDetails.size()>0){
            for(VehicleDetail v:vehicleDetails){
                if(v.getDabh()!=null&&!"".equals(v.getDabh())){
                    List<VehicleFlow> vehFlowList = synchroLibraryMapper.findLsByDabh(v.getDabh());
                    if (vehFlowList==null||vehFlowList.size()==0){
                        vehFlowList = synchroLibraryMapper.findLsHisByDabh(v.getDabh());
                    }
                    if(vehFlowList!=null&&vehFlowList.size()>0){
                        v.setVehicleFlows(vehFlowList);
                    }
                }
            }
            return transformList(vehicleDetails);
        }else{
            return new ArrayList<>();
        }

    }

    @Override
    public List<ImageDetail> findAllImageByLsh(String lsh) throws TException {
        ImageInfo imageInfo=imageInfoMapper.findImageInfoByLsh(lsh);
        if(imageInfo!=null){
            String node=imageInfo.getNode();
            List<ImagePhotoRel> imagePhotoRels=imagePhotoRelMapper.getImagePhotoRelList(imageInfo.getImageId());
            if(imagePhotoRels!=null&&imagePhotoRels.size()>0){
                List<ImageDetail> imageDetailList=new ArrayList<>();
                List<BusinessTypeDetail> businessTypeDetails=businessTypeMapper.findBusinessTypeDetailByBstIdAndIsSm(imageInfo.getBusinessTypeInfoId(), ConstantsEnum.isSmStatus.扫描.getValue());
                Map<Integer,String> detailMap=new HashMap<>();
                for(BusinessTypeDetail b:businessTypeDetails){
                    detailMap.put(b.getBusinessDetailId(),b.getDetailName());
                }
                for(ImagePhotoRel p:imagePhotoRels){
                    ImageDetail imageDetail=new ImageDetail();
                    imageDetail.setImageCode(p.getPhoto());
                    imageDetail.setSrc(p.getUrl());
                    imageDetail.setName(p.getBtdId()==0?"":detailMap.get(p.getBtdId()));
                    imageDetailList.add(imageDetail);
                }
                return imageDetailList;
            }else{
                return new ArrayList<>();
            }

        }
        return new ArrayList<>();
    }

    @Override
    public List<ImageInfo> findAllImageInfoByBusinesses(List<Business> businessList) throws TException {
        StringBuffer sb=new StringBuffer("(");
        for(Business b:businessList){
            sb.append("'").append(b.getNumber()).append("',");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        List<ImageInfo> imageInfoList=imageInfoMapper.findAllImageInfoByLshs(sb.toString());
        if(imageInfoList!=null&&imageInfoList.size()>0){
            return imageInfoList;
        }else{
            return new ArrayList<>();
        }
    }


    public List<VehicleDetailDTO> transformList(List<VehicleDetail> list){
        List<VehicleDetailDTO> detailDTOs=new ArrayList<>();
        for(VehicleDetail v:list){
            VehicleDetailDTO dto=new VehicleDetailDTO();
            dto.setName(v.getSyr());
            dto.setPlateNo(v.getHphm());
            dto.setVin(v.getClsbdh());
            dto.setEngineNo(v.getFdjh());
            dto.setPlateType(v.getHpzl());
            dto.setVehicleType(v.getCllx());
            //dto.setBrands("中文品牌");
            dto.setModel(v.getClxh());
            dto.setCarColor(v.getCsys());
            dto.setUseType(v.getSyxz());
            dto.setCarStatus(v.getZt());
            dto.setRegisterDate(v.getCcdjrq());
            //dto.setPeriod(new SimpleDateFormat("yyyy-MM-dd").format(v.getYxqz()));
            dto.setMobile(v.getSjhm());
            dto.setVehicleflow(v.getVehicleFlows());
            detailDTOs.add(dto);
        }
        return detailDTOs;
    }

}
