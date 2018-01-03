package com.igool.ssp.web.controller;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.BusinessTypeDetail;
import com.igool.rpc.db.model.thrift.BusinessTypeInfo;
import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.BusinessTypeService;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.util.ConstantsEnum;
import com.igool.ssp.web.util.StringUtils;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wang on 2017/10/12.
 */
@Controller
@AclAnnotation(pUrl = "/showSmy")
public class SmyController {
    Logger logger= LoggerFactory.getLogger(SmyController.class);
    @Autowired
    BusinessInfoService businessInfoService;
    @Autowired
    BusinessTypeService businessTypeService;
    @Autowired
    ImagePhotoRefService imagePhotoRelService;
    @Value("#{config['FILEURL']}")
    String FILEURL;
    @Value("#{config['UPLOADURL']}")
    String UPLOADURL;
    @RequestMapping("/showSmy")
    public ModelAndView showSmy(HttpServletRequest request,String businessCode,Integer userId) throws TException {
        BusinessInfo businessInfo=businessInfoService.findNativeBusinessInfoByLsh(businessCode);
        String ywlx = "";
        String msg = "";
        int i = 1;

        if (null != businessInfo.getBusinessType())
            ywlx = businessInfo.getBusinessType();

        String nameDetails = "";
        String lastName = "";
        //根据业务类型获取业务类型明细
        List<BusinessTypeDetail> businessTypeDetails=businessTypeService.findBusinessTypeDetailByYwlx(ywlx);
        //根据业务类型找到业务类型id，再根据流水号和业务类型id找到，imagephote,再根据imageid找到所有的图片
        BusinessTypeInfo businessTypeInfo=businessTypeService.findBusinessTypeByYwlx(ywlx);
        List<ImagePhotoRel> imagePhotoRelList=imagePhotoRelService.findImageRelByLshAndBusinessTypeId(businessCode,businessTypeInfo.getBusinessTypeId());
        Multimap<Integer, ImagePhotoRel> myMultimap = ArrayListMultimap.create();
        if(imagePhotoRelList!=null&& imagePhotoRelList.size()>0){
            for(ImagePhotoRel ipr:imagePhotoRelList){
                myMultimap.put(ipr.getBtdId(),ipr);
            }
        }
        if(businessTypeDetails.size()>0){
            for(BusinessTypeDetail b:businessTypeDetails){
                if (!"其他".equals(b.getDetailName())){
                    nameDetails+=b.getDetailName()+";";
                }else{
                    lastName = b.getDetailName();
                }
                int detailId = b.getBusinessDetailId();
                int status = b.getIsSm();
                String info = "";
                if(myMultimap!=null&&myMultimap.size()>0){
                    if(myMultimap.containsKey(b.getBusinessDetailId())){
                        Iterator iterator=myMultimap.get(b.getBusinessDetailId()).iterator();
                        while (iterator.hasNext()){
                            ImagePhotoRel imagePhotoRel= (ImagePhotoRel) iterator.next();
                            String photo = imagePhotoRel.getPhoto();
                            String url = imagePhotoRel.getUrl();
                            if (StringUtils.isNotEmptyByTrim(photo)) {
                                String detailName = b.getDetailName();
                                logger.info("关联图片, detailId:"+detailId+", detailName:"+detailName+", photo:"+photo);
                                info += photo + "_" + detailId + ", " + detailName;
                                if (StringUtils.isNotEmptyByTrim(url)) {
                                    info += "," + url + ";";
                                }else{
                                    info += "," + FILEURL + photo + ";";
                                }
                            }
                        }
                    }else{
                        logger.info("没有相关联图片, detailId:"+detailId+", status:"+status+", detailName:"+b.getDetailName());
                        if (ConstantsEnum.isSmStatus.扫描.getValue().equals(status)) {
                            info = "add_" + detailId + "," + b.getDetailName() + ";";
                        }
                    }
                }
                /*if (imagePhotoRelList!=null&&imagePhotoRelList.size() > 0) {
                    int count=0;
                    for (ImagePhotoRel imagePhotoRelDTO : imagePhotoRelList) {

                        if(imagePhotoRelDTO.getBtdId()==b.getBusinessDetailId()){
                            count++;
                            String photo = imagePhotoRelDTO.getPhoto();
                            String url = imagePhotoRelDTO.getUrl();
                            if (StringUtils.isNotEmptyByTrim(photo)) {
                                String detailName = b.getDetailName();
                                logger.info("关联图片, detailId:"+detailId+", detailName:"+detailName+", photo:"+photo);
                                info += photo + "_" + detailId + ", " + detailName;
                                if (StringUtils.isNotEmptyByTrim(url)) {
                                    info += "," + url + ";";
                                }else{
                                    info += "," + FILEURL + photo + ";";
                                }
                            }
                        }
                    }
                    if(count==0){
                        logger.info("没有相关联图片, detailId:"+detailId+", status:"+status+", detailName:"+b.getDetailName());
                        if (ConstantsEnum.isSmStatus.扫描.getValue().equals(status)) {
                            info = "add_" + detailId + "," + b.getDetailName() + ";";
                        }
                    }
                }*/ else {
                    logger.info("没有相关联图片, detailId:"+detailId+", status:"+status+", detailName:"+b.getDetailName());
                    if (ConstantsEnum.isSmStatus.扫描.getValue().equals(status)) {
                        info = "add_" + detailId + "," + b.getDetailName() + ";";
                    }
                }
                msg += info;
                i++;
            }
        }else{
            logger.info("该业务类型没有配置");
        }
        nameDetails+=lastName;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("smy/smy");
        modelAndView.addObject("path", UPLOADURL);
        modelAndView.addObject("lsh", businessCode);
        modelAndView.addObject("id",userId);
        modelAndView.addObject("msg", msg);
        modelAndView.addObject("nameDetails",nameDetails);
        return modelAndView;
    }
}
